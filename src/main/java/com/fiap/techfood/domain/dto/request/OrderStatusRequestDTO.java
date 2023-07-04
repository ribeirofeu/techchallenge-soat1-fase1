package com.fiap.techfood.domain.dto.request;

import com.fiap.techfood.domain.OrderStatus;

import java.time.OffsetDateTime;

public record OrderStatusRequestDTO(OrderStatus status, OffsetDateTime paymentDate) {}