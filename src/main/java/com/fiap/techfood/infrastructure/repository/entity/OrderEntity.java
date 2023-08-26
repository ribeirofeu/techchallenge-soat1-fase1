package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.entities.Order;
import com.fiap.techfood.domain.entities.OrderStatus;
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
    private OffsetDateTime dateTime;

    @Column(name = "`notes`")
    private String notes;

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
                .customer(order.getCustomer() != null ? new CustomerEntity(order.getCustomer()) : null)
                .dateTime(order.getDateTime())
                .notes(order.getNotes())
                .status(order.getStatus())
                .totalValue(order.getTotalValue())
                .build();
    }

    public Order toOrder() {
        Order order = new Order();
        order.setCustomer(Objects.nonNull(this.customer) ? this.customer.toCustomer() : null);
        order.setStatus(this.status);
        order.setTotalValue(this.totalValue);
        order.setDateTime(this.dateTime);
        order.setNumber(this.id);
        order.setNotes(this.getNotes());
        order.setItems(this.items.stream().map(OrderItemEntity::toOrderItem).collect(Collectors.toList()));
        return order;
    }
}
