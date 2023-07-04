package com.fiap.techfood.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.techfood.domain.dto.request.OrderRequestDTO;
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
    private OffsetDateTime dateTime;
    private String notes;
    private OffsetDateTime paymentDate;

    public static Order fromOrderRequestDTO(OrderRequestDTO requestDTO) {
        return Order.builder()
                .notes(requestDTO.getNotes())
                .build();
    }
}
