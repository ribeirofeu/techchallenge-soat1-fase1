package com.fiap.techfood.application.dto.request;

import com.fiap.techfood.domain.order.OrderPaymentStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProcessOrderPaymentRequestDTO {
    private Long orderId;
    private OrderPaymentStatus paymentStatus;
}
