package com.fiap.techfood.application.interfaces.gateways;


import com.fiap.techfood.domain.order.Order;
import com.fiap.techfood.domain.order.OrderStatus;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long id);

    Order save(Order order);

    void updateOrderStatus(Order order);

    List<Order> findAllNotCompleted();

    List<Order> findOrdersByStatusAndTimeInterval(OrderStatus status, OffsetDateTime startDateTime, OffsetDateTime endDateTime);
}
