package com.fiap.techfood.application.handler.model;

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
