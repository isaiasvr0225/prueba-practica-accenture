package com.accenture.presentation.controller.product;

import com.accenture.domain.exception.BranchException;
import com.accenture.domain.exception.ProductException;
import com.accenture.infrastructure.persistence.dto.branch.BranchErrorDTO;
import com.accenture.infrastructure.persistence.dto.product.ProductErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @package : com.accenture.presentation.controller
 * @name : ProductControllerAdvice.java
 * @date : 2024-12
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@RestControllerAdvice
public class ProductControllerAdvice {

    /**
     * This method is used to handle BranchException
     * @param productException productException
     * @return ResponseEntity<ProductErrorDTO>
     */
    @ExceptionHandler(value = ProductException.class)
    public ResponseEntity<ProductErrorDTO> handleBranchOperationException(ProductException productException) {
        return ResponseEntity.status(productException.getHttpStatus()).body(ProductErrorDTO.builder()
                .code(productException.getCode())
                .message(productException.getMessage())
                .build());
    }
}
