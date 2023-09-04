package com.fiap.techfood.domain.commons.exception;

import com.fiap.techfood.domain.commons.HttpStatusCodes;
import lombok.Getter;

public class BusinessException extends RuntimeException{

    @Getter
    private int httpStatus;

    public BusinessException (String message, HttpStatusCodes code) {
        super(message);
        this.httpStatus = code.getCode();
    }

}
