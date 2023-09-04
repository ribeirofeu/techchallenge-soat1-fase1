package com.fiap.techfood.application.dto.request;

import com.fiap.techfood.domain.order.OrderStatus;
public record OrderStatusRequestDTO(OrderStatus status) {}