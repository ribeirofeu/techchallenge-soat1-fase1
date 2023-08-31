package com.fiap.techfood.domain.ports.repositories;


import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.OrderStatus;

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
