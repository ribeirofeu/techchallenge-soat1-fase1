package com.fiap.techfood.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{

    @Getter
    private HttpStatus httpStatus;

    public BusinessException (String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
