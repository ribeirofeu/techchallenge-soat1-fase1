package com.fiap.techfood.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    CREATED(0),
    RECEIVED(3),
    IN_PREPARATION(2),
    READY(1),
    COMPLETED(0),
    REJECTED(0);

    private final int displayPriority;
}