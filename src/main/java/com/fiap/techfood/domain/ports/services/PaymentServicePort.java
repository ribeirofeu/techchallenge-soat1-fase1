package com.fiap.techfood.domain.ports.services;

import com.fiap.techfood.domain.Order;

import java.math.BigDecimal;

public interface PaymentServicePort {
    String getPaymentQRCode(Order order);
}
