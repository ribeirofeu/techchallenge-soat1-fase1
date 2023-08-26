package com.fiap.techfood.domain.interfaces.usecases;

import com.fiap.techfood.domain.dto.request.OrderRequestDTO;
import com.fiap.techfood.domain.entities.Order;
import com.fiap.techfood.domain.entities.OrderStatus;

import java.time.OffsetDateTime;
import java.util.List;

public interface IOrderUseCases {
    Order createOrder(OrderRequestDTO requestDTO);

    Order updateOrderStatus(Long orderNumber, OrderStatus status);

    List<Order> findOrdersByStatusAndTimeInterval(OrderStatus status, OffsetDateTime startDateTime, OffsetDateTime endDatetime);
}
