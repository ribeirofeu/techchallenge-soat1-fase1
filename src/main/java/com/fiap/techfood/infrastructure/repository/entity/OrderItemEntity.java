package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.OrderItem;
import com.fiap.techfood.domain.Product;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
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
}
