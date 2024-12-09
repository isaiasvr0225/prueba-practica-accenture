package com.accenture.presentation.controller.product;

import com.accenture.application.service.product.ProductService;
import com.accenture.infrastructure.persistence.dto.product.ProductRequestDTO;
import com.accenture.infrastructure.persistence.dto.product.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @package : com.accenture.presentation.controller
 * @name : ProductController.java
 * @date : 2024-12
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@RequiredArgsConstructor
@RequestMapping("/api/v1/branches")
public @RestController class ProductController {

    private final ProductService productService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.productService.findAll(pageable).join());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.productService.findById(id).join());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public HttpStatus save(@RequestBody ProductRequestDTO productRequestDTO) {

        return this.productService.save(productRequestDTO).join();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public HttpStatus updateStock(@PathVariable(name = "id") Long id, @RequestParam Integer newStockQuantity) {
        return this.productService.updateStock(id, newStockQuantity).join();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable(name = "id") Long id) {
        return this.productService.delete(id).join();
    }
}
