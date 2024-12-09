package com.accenture.presentation.controller.branch;

import com.accenture.application.service.branch.BranchService;
import com.accenture.infrastructure.persistence.dto.branch.BranchRequestDTO;
import com.accenture.infrastructure.persistence.dto.branch.BranchResponseDTO;
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
 * @name : BranchController.java
 * @date : 2024-12
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@RequiredArgsConstructor
@RequestMapping("/api/v1/branches")
public @RestController class BranchController {

    private final BranchService branchService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<BranchResponseDTO>> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.branchService.findAll(pageable).join());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<BranchResponseDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.branchService.findById(id).join());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public HttpStatus save(@RequestBody BranchRequestDTO branchRequestDTO) {

        return this.branchService.save(branchRequestDTO).join();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public HttpStatus updateBranchName(@PathVariable(name = "id") Long id, @RequestParam String newStockQuantity) {
        return this.branchService.updateBranchName(id, newStockQuantity).join();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable(name = "id") Long id) {
        return this.branchService.delete(id).join();
    }
}
