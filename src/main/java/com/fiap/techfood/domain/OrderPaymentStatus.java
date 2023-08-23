package com.fiap.techfood.domain;

import lombok.Getter;

@Getter
public enum OrderPaymentStatus {
    APPROVED,
    PENDING,
    REJECTED;
}
