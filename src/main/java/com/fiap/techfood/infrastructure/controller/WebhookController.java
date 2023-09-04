package com.fiap.techfood.application.controller;

import com.fiap.techfood.application.dto.request.ProcessOrderPaymentRequestDTO;
import com.fiap.techfood.application.interfaces.usecases.OrderUseCases;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private OrderUseCases useCases;

    @PostMapping("/order/payment")
    public ResponseEntity<Void> orderPaymentWebhook(@RequestBody @Valid ProcessOrderPaymentRequestDTO orderPaymentRequest) {
        useCases.processOrderPayment(orderPaymentRequest);
        return ResponseEntity.ok().build();
    }
}
