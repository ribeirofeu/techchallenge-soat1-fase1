package com.fiap.techfood.domain.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.domain.customer.Customer;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    private Long number;
    private Customer customer;
    private List<OrderItem> items;
    private BigDecimal totalValue;
    private OrderStatus status;
    private OffsetDateTime createdDateTime;
    private OffsetDateTime receivedDateTime;
    private String notes;
    private String qrCode;

    public static Order fromOrderRequestDTO(OrderRequestDTO requestDTO) {
        return Order.builder()
                .notes(requestDTO.getNotes())
                .build();
    }
}
