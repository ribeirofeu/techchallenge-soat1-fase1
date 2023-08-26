package com.fiap.techfood.domain.interfaces.gateway;


import com.fiap.techfood.domain.entities.Order;
import com.fiap.techfood.domain.entities.OrderStatus;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long id);

    Order save(Order order);

    void updateOrderStatus(Order order);

    List<Order> findOrdersByStatusAndTimeInterval(OrderStatus status, OffsetDateTime startDateTime, OffsetDateTime endDateTime);
}
