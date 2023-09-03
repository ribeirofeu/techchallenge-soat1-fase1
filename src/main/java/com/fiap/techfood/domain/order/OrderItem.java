package com.fiap.techfood.domain.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.domain.products.Product;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItem {
    private Order order;
    private Product product;
    private Integer quantity;
    private BigDecimal unitPrice;

    public static OrderItem fromOrderItemRequestDTO(OrderRequestDTO.OrderItemRequestDTO requestDTO) {
        return OrderItem.builder()
                .product(Product.builder().id(requestDTO.getProductId()).build())
                .quantity(requestDTO.getQuantity())
                .build();
    }

    public BigDecimal calculateItemTotalValue() {
        return this.unitPrice.multiply(BigDecimal.valueOf(this.getQuantity()));
    }

}
