package com.fiap.techfood.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    RECEIVED,
    PAID,
    IN_PREPARATION,
    READY,
    COMPLETED;
}
