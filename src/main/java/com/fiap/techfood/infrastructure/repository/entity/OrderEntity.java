package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.order.Order;
import com.fiap.techfood.domain.order.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@Entity(name = "Order")
@Table(name = "`order`")
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "`status`", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    @Column(name = "`datetime`", nullable = false)
    private OffsetDateTime createdDateTime;

    @Column(name = "`received_datetime`")
    private OffsetDateTime receivedDateTime;

    @Column(name = "`notes`")
    private String notes;

    @Transient
    private String qrCode;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<OrderItemEntity> items = new ArrayList<>();

    public void addItem(ProductEntity product, Integer quantity) {
        OrderItemEntity orderItemEntity = new OrderItemEntity(this, product, quantity);
        items.add(orderItemEntity);
    }

    public static OrderEntity from(Order order) {
        return OrderEntity.builder()
                .id(order.getNumber())
                .customer(order.getCustomer() != null ? new CustomerEntity(order.getCustomer()) : null)
                .createdDateTime(order.getCreatedDateTime())
                .receivedDateTime(order.getReceivedDateTime())
                .notes(order.getNotes())
                .status(order.getStatus())
                .totalValue(order.getTotalValue())
                .qrCode(order.getQrCode())
                .build();
    }

    public Order toOrder() {
        return Order.builder()
                .customer(Objects.nonNull(this.customer) ? this.customer.toCustomer() : null)
                .status(this.status)
                .totalValue(this.totalValue)
                .createdDateTime(this.createdDateTime)
                .receivedDateTime(this.receivedDateTime)
                .number(this.id)
                .notes(this.getNotes())
                .qrCode(this.getQrCode())
                .items(this.items.stream().map(OrderItemEntity::toOrderItem).collect(Collectors.toList()))
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
