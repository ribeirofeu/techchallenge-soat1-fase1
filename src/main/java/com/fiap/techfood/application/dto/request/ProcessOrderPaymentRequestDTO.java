package com.fiap.techfood.domain.dto.request;

import com.fiap.techfood.domain.OrderPaymentStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProcessOrderPaymentRequestDTO {
    private Long orderId;
    private OrderPaymentStatus paymentStatus;
}
