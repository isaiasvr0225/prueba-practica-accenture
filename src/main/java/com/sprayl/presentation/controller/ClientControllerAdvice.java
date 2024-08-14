package com.sprayl.presentation.controller;

import com.sprayl.domain.exception.ClientException;
import com.sprayl.infrastructure.persistence.dto.ClientErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @package : com.sprayl.presentation.controller
 * @name : ClientControllerAdvice.java
 * @date : 2024-08
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@RestControllerAdvice
public class ClientControllerAdvice {

    /**
     * This method is used to handle ClientException
     * @param ClientException clientException
     * @return ResponseEntity<ClientErrorDto>
     */
    @ExceptionHandler(value = ClientException.class)
    public ResponseEntity<ClientErrorDto> handleTaskException(ClientException clientException) {
        return ResponseEntity.status(clientException.getHttpStatus()).body(ClientErrorDto.builder()
                .code(clientException.getCode())
                .message(clientException.getMessage())
                .build());
    }
}
