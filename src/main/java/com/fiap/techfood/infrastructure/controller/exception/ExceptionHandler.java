package com.fiap.techfood.infrastructure.controller.exception;

import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.infrastructure.handler.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessException(BusinessException be) {
        ErrorResponse errorResponse = new ErrorResponse(be.getMessage());
        return ResponseEntity.status(be.getHttpStatus()).body(errorResponse);
    }
}
