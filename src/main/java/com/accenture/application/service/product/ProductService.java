package com.accenture.application.service.product;

import com.accenture.infrastructure.persistence.dto.branch.BranchRequestDTO;
import com.accenture.infrastructure.persistence.dto.branch.BranchResponseDTO;
import com.accenture.infrastructure.persistence.dto.product.ProductRequestDTO;
import com.accenture.infrastructure.persistence.dto.product.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.concurrent.CompletableFuture;

/**
 * @package : com.accenture.application.service
 * @name : ProductService.java
 * @date : 2024-12
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
public interface ProductService {

    /**
     * This method is used to find all products using pagination
     * @param pageable pageable
     * @return CompletableFuture<Page<ProductResponseDTO>>
     */
    CompletableFuture<Page<ProductResponseDTO>> findAll(Pageable pageable);

    /**
     * This method is used to find a product by id
     * @param id id
     * @return CompletableFuture<ProductResponseDTO>
     */
    CompletableFuture<ProductResponseDTO> findById(Long id);

    /**
     * This method is used to save a product
     * @param productRequestDTO productRequestDTO
     * @return CompletableFuture<HttpStatus>
     */
    CompletableFuture<HttpStatus> save(ProductRequestDTO productRequestDTO);

    /**
     * This method is used to update a product stock
     * @param productId productId
     * @param newStockQuantity newStockQuantity
     * @return CompletableFuture<HttpStatus>
     */
    CompletableFuture<HttpStatus> updateStock(Long productId, Integer newStockQuantity);

    /**
     * This method is used to delete a product
     * @param id id
     * @return CompletableFuture<HttpStatus>
     */
    CompletableFuture<HttpStatus> delete(Long id);

}
