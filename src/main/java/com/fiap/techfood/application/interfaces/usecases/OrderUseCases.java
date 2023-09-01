package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.OrderStatus;
import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.application.dto.request.ProcessOrderPaymentRequestDTO;
import com.fiap.techfood.application.dto.request.SearchOrdersRequestDTO;
import com.fiap.techfood.application.dto.response.OrderPaymentStatusDTO;

import java.util.List;

public interface OrderServicePort {
    Order createOrder(OrderRequestDTO requestDTO);

    Order updateOrderStatus(Long orderNumber, OrderStatus status);

    List<Order> findOrdersByStatusAndTimeInterval(SearchOrdersRequestDTO searchOrdersRequestDTO);

    List<Order> findNotCompletedOrders();

    OrderPaymentStatusDTO getOrderPaymentStatus(Long orderNumber);

    void processOrderPayment(ProcessOrderPaymentRequestDTO processOrderPaymentRequest);
}
