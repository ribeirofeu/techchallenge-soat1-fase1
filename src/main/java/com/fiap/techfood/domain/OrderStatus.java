package com.fiap.techfood.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED,
    RECEIVED,
    IN_PREPARATION,
    READY,
    COMPLETED,
    CANCELLED;
}
