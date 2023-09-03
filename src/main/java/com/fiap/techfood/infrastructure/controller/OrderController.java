package com.fiap.techfood.infrastructure.controller;

import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.application.dto.request.OrderStatusRequestDTO;
import com.fiap.techfood.application.dto.request.SearchOrdersRequestDTO;
import com.fiap.techfood.application.dto.response.OrderPaymentStatusDTO;
import com.fiap.techfood.application.interfaces.usecases.OrderUseCases;
import com.fiap.techfood.domain.order.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Pedidos")
public class OrderController {

    @Autowired
    private OrderUseCases useCases;

    @PostMapping
    @Operation(summary = "Cria um pedido")
    ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO request) {
        Order order = useCases.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PatchMapping("/{orderNumber}")
    @Operation(summary = "Atualiza o status de um pedido")
    ResponseEntity<Order> updateOrder(@PathVariable Long orderNumber, @RequestBody OrderStatusRequestDTO orderStatusRequestDTO) {
        Order order = useCases.updateOrderStatus(orderNumber, orderStatusRequestDTO.status());
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping("/")
    @Operation(summary = "Lista pedidos não finalizados ordenados por status")
    ResponseEntity<List<Order>> findNotCompletedOrders() {
        return ResponseEntity.ok(useCases.findNotCompletedOrders());
    }

    @GetMapping("/status")
    @Operation(summary = "Lista todos os pedidos a partir de um status e um intervalo de tempo")
    ResponseEntity<List<Order>> findOrdersByStatusAndTimeInterval(@Valid SearchOrdersRequestDTO searchOrdersRequestDTO) {
        return ResponseEntity.ok(useCases.findOrdersByStatusAndTimeInterval(searchOrdersRequestDTO));
    }

    @GetMapping("/{orderNumber}/payment/status")
    @Operation(summary = "Envia o status do pagamento do pedido")
    ResponseEntity<OrderPaymentStatusDTO> findOrdersByStatusAndTimeInterval(Long orderNumber) {
        return ResponseEntity.ok(useCases.getOrderPaymentStatus(orderNumber));
    }
}
