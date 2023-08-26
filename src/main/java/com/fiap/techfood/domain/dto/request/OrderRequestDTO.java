package com.fiap.techfood.domain.dto.request;

import java.util.List;

public class OrderRequestDTO {
    private Long customerId;
    private List<OrderItemRequestDTO> items;
    private String notes;

    public static class OrderItemRequestDTO {
        private Long productId;
        private Integer quantity;

        public Long getProductId() {
            return this.productId;
        }

        public Integer getQuantity() {
            return this.quantity;
        }
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getNotes() {
        return notes;
    }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }
}
