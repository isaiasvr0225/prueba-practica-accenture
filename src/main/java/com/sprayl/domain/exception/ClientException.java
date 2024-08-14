package com.sprayl.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ClientException extends RuntimeException{

    private String code;
    private HttpStatus httpStatus;

    public ClientException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
