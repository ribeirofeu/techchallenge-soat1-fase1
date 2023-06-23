package com.fiap.techfood.domain.ports.services;

import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.OrderStatus;
import com.fiap.techfood.domain.dto.request.OrderRequestDTO;

import java.time.OffsetDateTime;
import java.util.List;

public interface OrderServicePort {
    Order createOrder(OrderRequestDTO requestDTO);

    Order updateOrderStatus(Long orderNumber, OrderStatus status);

    List<Order> findOrdersByStatusAndTimeInterval(OrderStatus status, OffsetDateTime startDateTime, OffsetDateTime endDateTime);
}
