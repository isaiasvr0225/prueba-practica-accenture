package com.accenture.presentation.controller.product;

import com.accenture.domain.exception.UserException;
import com.accenture.infrastructure.persistence.dto.user.UserErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @package : com.sprayl.presentation.controller
 * @name : UserControllerAdvice.java
 * @date : 2024-08
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@RestControllerAdvice
public class UserControllerAdvice {

    /**
     * This method is used to handle UserException
     * @param ClientException userException
     * @return ResponseEntity<UserErrorDTO>
     */
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<UserErrorDTO> handleTaskException(UserException userException) {
        return ResponseEntity.status(userException.getHttpStatus()).body(UserErrorDTO.builder()
                .code(userException.getCode())
                .message(userException.getMessage())
                .build());
    }
}
