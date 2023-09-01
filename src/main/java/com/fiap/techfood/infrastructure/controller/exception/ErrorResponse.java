package com.fiap.techfood.infrastructure.handler.exception;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ErrorResponse {

    private OffsetDateTime timestamp;
    private String messageError;

    public ErrorResponse(String messageError) {
        this.timestamp = OffsetDateTime.now();
        this.messageError = messageError;
    }
}
