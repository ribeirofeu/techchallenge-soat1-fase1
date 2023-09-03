package com.fiap.techfood.application.dto.response;

import com.fiap.techfood.domain.order.OrderPaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderPaymentStatusDTO {
    private OrderPaymentStatus status;
}
