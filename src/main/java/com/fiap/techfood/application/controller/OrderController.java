package com.fiap.techfood.application.controller;

import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.dto.request.CategoryRequestDTO;
import com.fiap.techfood.domain.dto.request.OrderRequestDTO;
import com.fiap.techfood.domain.ports.services.OrderServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
