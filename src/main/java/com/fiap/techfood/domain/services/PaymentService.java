package com.fiap.techfood.domain.services;

import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.ports.services.PaymentServicePort;

import java.math.BigDecimal;

public class PaymentService implements PaymentServicePort {
    @Override
    public String getPaymentQRCode(Order order) {
        return "valid_qr_code";
    }
}
