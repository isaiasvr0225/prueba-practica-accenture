package com.accenture.presentation.controller.branch;

import com.accenture.domain.exception.BranchException;
import com.accenture.domain.exception.UserException;
import com.accenture.infrastructure.persistence.dto.branch.BranchErrorDTO;
import com.accenture.infrastructure.persistence.dto.user.UserErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @package : com.accenture.presentation.controller
 * @name : BranchControllerAdvice.java
 * @date : 2024-12
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@RestControllerAdvice
public class BranchControllerAdvice {

    /**
     * This method is used to handle BranchException
     * @param branchException branchException
     * @return ResponseEntity<BranchErrorDTO>
     */
    @ExceptionHandler(value = BranchException.class)
    public ResponseEntity<BranchErrorDTO> handleBranchOperationException(BranchException branchException) {
        return ResponseEntity.status(branchException.getHttpStatus()).body(BranchErrorDTO.builder()
                .code(branchException.getCode())
                .message(branchException.getMessage())
                .build());
    }
}
