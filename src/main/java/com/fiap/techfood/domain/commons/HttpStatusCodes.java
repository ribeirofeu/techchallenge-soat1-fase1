package com.fiap.techfood.domain.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpStatusCodes {
    NOT_FOUND(404),
    BAD_REQUEST(400);

    private final int code;
}
