package com.accenture.presentation.controller.franchise;

import com.accenture.domain.exception.BranchException;
import com.accenture.domain.exception.FranchiseException;
import com.accenture.infrastructure.persistence.dto.branch.BranchErrorDTO;
import com.accenture.infrastructure.persistence.dto.franchise.FranchiseErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @package : com.accenture.presentation.controller
 * @name : FranchiseControllerAdvice.java
 * @date : 2024-12
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@RestControllerAdvice
public class FranchiseControllerAdvice {

    /**
     * This method is used to handle BranchException
     * @param franchiseException franchiseException
     * @return ResponseEntity<FranchiseErrorDTO>
     */
    @ExceptionHandler(value = FranchiseException.class)
    public ResponseEntity<FranchiseErrorDTO> handleBranchOperationException(FranchiseException franchiseException) {
        return ResponseEntity.status(franchiseException.getHttpStatus()).body(FranchiseErrorDTO.builder()
                .code(franchiseException.getCode())
                .message(franchiseException.getMessage())
                .build());
    }
}
