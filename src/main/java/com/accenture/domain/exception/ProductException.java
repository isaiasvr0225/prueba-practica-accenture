package com.accenture.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ProductException extends RuntimeException{

    private String code;
    private HttpStatus httpStatus;

    public ProductException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
