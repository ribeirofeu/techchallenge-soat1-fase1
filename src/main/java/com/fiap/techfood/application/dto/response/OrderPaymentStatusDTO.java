package com.fiap.techfood.domain.dto.response;

import com.fiap.techfood.domain.OrderPaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderPaymentStatusDTO {
    private OrderPaymentStatus status;
}
