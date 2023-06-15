package com.fiap.techfood.application.handler.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Data
public class ErrorResponse {

    private OffsetDateTime timestamp;
    private String messageError;
    private String path;

    public ErrorResponse(String messageError, String path) {
        this.timestamp = OffsetDateTime.now();
        this.messageError = messageError;
        this.path = path;
    }
}
