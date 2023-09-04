package com.fiap.techfood.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private Long customerId;
    private List<OrderItemRequestDTO> items;
    private String notes;

    @Getter
    @Builder
    public static class OrderItemRequestDTO {
        private Long productId;
        private Integer quantity;
    }
}
