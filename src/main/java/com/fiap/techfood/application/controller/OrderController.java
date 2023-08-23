package com.fiap.techfood.application.controller;

import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.dto.request.OrderRequestDTO;
import com.fiap.techfood.domain.dto.request.OrderStatusRequestDTO;
import com.fiap.techfood.domain.dto.request.SearchOrdersRequestDTO;
import com.fiap.techfood.domain.dto.response.OrderPaymentStatusDTO;
import com.fiap.techfood.domain.ports.services.OrderServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Pedidos")
public class OrderController {

    @Autowired
    private OrderServicePort orderService;

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
    ResponseEntity<List<Order>> findOrdersByStatusAndTimeInterval(@Valid SearchOrdersRequestDTO searchOrdersRequestDTO) {
        return ResponseEntity.ok(orderService.findOrdersByStatusAndTimeInterval(searchOrdersRequestDTO));
    }

    @GetMapping("/{orderNumber}/payment/status")
    @Operation(summary = "Envia o status do pagamento do pedido")
    ResponseEntity<OrderPaymentStatusDTO> findOrdersByStatusAndTimeInterval(Long orderNumber) {
        return ResponseEntity.ok(orderService.getOrderPaymentStatus(orderNumber));
    }
}
