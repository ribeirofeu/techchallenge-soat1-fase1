package com.fiap.techfood.application.controller;

import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.dto.request.OrderRequestDTO;
import com.fiap.techfood.domain.dto.request.OrderStatusRequestDTO;
import com.fiap.techfood.domain.dto.request.SearchOrdersRequestDTO;
import com.fiap.techfood.domain.ports.services.OrderServicePort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServicePort orderService;

    @PostMapping
    ResponseEntity<Order> createCategory(@RequestBody OrderRequestDTO request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PatchMapping("/{orderNumber}")
    ResponseEntity<Order> updateOrder(@PathVariable Long orderNumber, @RequestBody OrderStatusRequestDTO orderStatusRequestDTO) {
        Order order = orderService.updateOrderStatus(orderNumber, orderStatusRequestDTO.status());
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping("/")
    ResponseEntity<List<Order>> findOrdersByStatusAndTimeInterval(@Valid SearchOrdersRequestDTO searchOrdersRequestDTO) {
        return ResponseEntity.ok(orderService.findOrdersByStatusAndTimeInterval(searchOrdersRequestDTO));
    }
}
