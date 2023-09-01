package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.domain.Order;

public interface PaymentServicePort {
    String getPaymentQRCode(Order order);
}
