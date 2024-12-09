package com.accenture.application.service.product;

import com.accenture.domain.entity.Branch;
import com.accenture.domain.entity.Product;
import com.accenture.domain.exception.BranchException;
import com.accenture.domain.exception.ProductException;
import com.accenture.domain.exception.UserException;
import com.accenture.infrastructure.persistence.dto.branch.BranchRequestDTO;
import com.accenture.infrastructure.persistence.dto.branch.BranchResponseDTO;
import com.accenture.infrastructure.persistence.dto.product.ProductRequestDTO;
import com.accenture.infrastructure.persistence.dto.product.ProductResponseDTO;
import com.accenture.infrastructure.persistence.repository.BranchRepository;
import com.accenture.infrastructure.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @package : com.accenture.application.service
 * @name : BranchServiceImpl.java
 * @date : 2024-12
 * @author  : Isaias Villarreal
 * @version : 1.0.0
 */

@RequiredArgsConstructor
public @Service class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final BranchRepository branchRepository;

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductService productService;


    /**
     * This method is used to find all products using pagination, also it is an asynchronous method
     * @param pageable pageable
     * @return CompletableFuture<Page<ProductResponseDTO>>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Page<ProductResponseDTO>> findAll(Pageable pageable) {

        Page<Product> pageProductEntity = this.productRepository.findAll(pageable);

        logger.info("pageProductEntity: " + pageProductEntity);

        if (pageProductEntity.isEmpty()) {
            logger.error("Internal server error");
            throw new BranchException("500", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Page<ProductResponseDTO> productResponseDTOS = pageProductEntity.map((productServiceEntity) -> ProductResponseDTO.builder()
                .id(productServiceEntity.getId())
                .name(productServiceEntity.getName())
                .stock(productServiceEntity.getStock())
                .branch(productServiceEntity.getBranch())
                .build());

        logger.info("productResponseDTOS: " + productResponseDTOS);

        return CompletableFuture.completedFuture(productResponseDTOS);
    }

    /**
     * This method is used to find a product by id, also it is an asynchronous method
     * @param id id
     * @return CompletableFuture<ProductResponseDTO>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<ProductResponseDTO> findById(Long id) {

        logger.info("id: " + id);

        Product productEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductException("404", "Product not found", HttpStatus.NOT_FOUND));

        logger.info("productEntity: " + productEntity);

        ProductResponseDTO productResponseDTO = ProductResponseDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .stock(productEntity.getStock())
                .branch(productEntity.getBranch())
                .build();

        logger.info("productResponseDTO: " + productResponseDTO);

        return CompletableFuture.completedFuture(productResponseDTO);

    }

    /**
     * This method is used to save a new product, also it is an asynchronous method
     * @param productRequestDTO productRequestDTO
     * @return CompletableFuture<HttpStatus>
     */
    @Override
    public CompletableFuture<HttpStatus> save(ProductRequestDTO productRequestDTO) {

        var branch = this.branchRepository.findById(productRequestDTO.branchId())
                .orElseThrow(() -> new BranchException("404", "Branch not found", HttpStatus.NOT_FOUND));

        Product productEntity = Product.builder()
                .name(productRequestDTO.name())
                .stock(productRequestDTO.stock())
                .branch(branch)
                .build();

        this.productRepository.save(productEntity);
        return CompletableFuture.completedFuture(HttpStatus.CREATED);
    }


    /**
     * This method is used to update a product stock, also it is an asynchronous method
     * @param productId productId
     * @param newStockQuantity newStockQuantity
     * @return CompletableFuture<HttpStatus>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<HttpStatus> updateStock(Long productId, Integer newStockQuantity) {


        Product productEntityFound = this.productRepository.findById(productId)
                .orElseThrow(() -> new BranchException("404", "Product not found", HttpStatus.NOT_FOUND));

        logger.info("productEntityFound: " + productEntityFound);

        this.productRepository.save(Product.builder()
                .id(productId)
                .stock(newStockQuantity)
                .build());
        return CompletableFuture.completedFuture(HttpStatus.OK);
    }

    /**
     * This method is used to delete a product by its id, also it is an asynchronous method
     * @param id id
     * @return CompletableFuture<Void>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<HttpStatus> delete(Long id) {

        if (id == null) {
            logger.error("Product id must not be null");
            throw new UserException("400", "Product id must not be null", HttpStatus.BAD_REQUEST);
        }

        this.productRepository.deleteById(id);

        return CompletableFuture.completedFuture(HttpStatus.OK);
    }


}
