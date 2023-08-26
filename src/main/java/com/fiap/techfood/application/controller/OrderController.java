package com.fiap.techfood.application.controller;

import com.fiap.techfood.domain.dto.request.OrderRequestDTO;
import com.fiap.techfood.domain.dto.request.OrderStatusRequestDTO;
import com.fiap.techfood.domain.entities.Order;
import com.fiap.techfood.domain.entities.OrderStatus;
import com.fiap.techfood.domain.interfaces.usecases.IOrderUseCases;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Pedidos")
public class OrderController {

    @Autowired
    private IOrderUseCases orderService;

    @PostMapping
    @Operation(summary = "Cria um pedido")
    ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PatchMapping("/{orderNumber}")
    @Operation(summary = "Atualiza o status de um pedido")
    ResponseEntity<Order> updateOrder(@PathVariable Long orderNumber, @RequestBody OrderStatusRequestDTO orderStatusRequestDTO) {
        Order order = orderService.updateOrderStatus(orderNumber, orderStatusRequestDTO.status());
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping("/")
    @Operation(summary = "Lista todos os pedidos a partir de um status e um intervalo de tempo")
    ResponseEntity<List<Order>> findOrdersByStatusAndTimeInterval(@RequestParam OrderStatus status,
                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                  OffsetDateTime startDatetime,
                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                  OffsetDateTime endDatetime) {
        return ResponseEntity.ok(orderService.findOrdersByStatusAndTimeInterval(status, startDatetime, endDatetime));
    }
}
