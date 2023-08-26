package com.fiap.techfood.domain.entities;

import com.fiap.techfood.domain.dto.request.OrderRequestDTO;

import java.math.BigDecimal;

public class OrderItem {
    private Order order;
    private Product product;
    private Integer quantity;
    private BigDecimal unitPrice;

    public static OrderItem fromOrderItemRequestDTO(OrderRequestDTO.OrderItemRequestDTO requestDTO) {
        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        product.setId(requestDTO.getProductId());
        orderItem.setProduct(product);
        orderItem.setQuantity(requestDTO.getQuantity());
        return orderItem;
    }

    public BigDecimal calculateItemTotalValue() {
        return this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
