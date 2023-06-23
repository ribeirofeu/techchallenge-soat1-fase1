package com.fiap.techfood.domain.ports.repositories;


import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.OrderStatus;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order findById(Long id);

    Order save(Order order);

    List<Order> findAll();

    List<Order> findOrdersByStatusAndTimeInterval(OrderStatus orderStatus, OffsetDateTime startDateTime, OffsetDateTime endDateTime);

    void deleteOrder(Long id);

    void updateOrder(Order order);
}
