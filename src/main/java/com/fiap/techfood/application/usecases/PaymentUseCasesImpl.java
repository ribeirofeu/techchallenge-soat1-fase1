package com.fiap.techfood.application.usecases;

import com.fiap.techfood.domain.Order;
import com.fiap.techfood.application.interfaces.usecases.PaymentUseCases;

public class PaymentService implements PaymentUseCases {
    @Override
    public String getPaymentQRCode(Order order) {
        return "valid_qr_code";
    }
}
