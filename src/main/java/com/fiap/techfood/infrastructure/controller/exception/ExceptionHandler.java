package com.fiap.techfood.infrastructure.controller.exception;

import com.fiap.techfood.infrastructure.controller.model.ErrorResponse;
import com.fiap.techfood.domain.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessException(BusinessException be) {
        ErrorResponse errorResponse = new ErrorResponse(be.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
