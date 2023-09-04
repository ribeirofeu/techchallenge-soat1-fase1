package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.order.OrderItem;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItemEntity {
    @EmbeddedId
    private OrderItemId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private ProductEntity product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    public OrderItemEntity(OrderEntity order, ProductEntity product, Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
        this.id = new OrderItemId(order.getId(), product.getId());
    }

    public OrderItem toOrderItem() {
        return OrderItem.builder()
                .product(this.product.toProduct())
                .unitPrice(this.unitPrice)
                .quantity(this.quantity)
                .build();
    }

    public static OrderItemEntity from(OrderItem orderItem) {
        return OrderItemEntity.builder()
                .id(OrderItemId.builder()
                        .orderId(orderItem.getOrder().getNumber())
                        .productId(orderItem.getProduct().getId())
                        .build())
                .product(ProductEntity.from(orderItem.getProduct()))
                .order(OrderEntity.from(orderItem.getOrder()))
                .unitPrice(orderItem.getUnitPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }
}
