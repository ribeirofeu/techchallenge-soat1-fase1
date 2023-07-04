package com.fiap.techfood.domain.ports.services;

import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.OrderStatus;
import com.fiap.techfood.domain.dto.request.OrderRequestDTO;
import com.fiap.techfood.domain.dto.request.SearchOrdersRequestDTO;

import java.time.OffsetDateTime;
import java.util.List;

public interface OrderServicePort {
    Order createOrder(OrderRequestDTO requestDTO);

    Order updateOrderStatus(Long orderNumber, OrderStatus status, OffsetDateTime paymentDate);

    List<Order> findOrdersByStatusAndTimeInterval(SearchOrdersRequestDTO searchOrdersRequestDTO);
}
