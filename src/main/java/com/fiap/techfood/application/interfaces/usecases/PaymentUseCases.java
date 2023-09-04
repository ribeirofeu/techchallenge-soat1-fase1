package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.domain.order.Order;

public interface PaymentUseCases {
    String getPaymentQRCode(Order order);
}
