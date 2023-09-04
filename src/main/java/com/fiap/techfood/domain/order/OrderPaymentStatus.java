package com.fiap.techfood.domain.order;

import lombok.Getter;

@Getter
public enum OrderPaymentStatus {
    APPROVED,
    PENDING,
    REJECTED;
}
