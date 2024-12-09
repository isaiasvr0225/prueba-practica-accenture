package com.accenture.presentation.controller.franchise;

import com.accenture.application.service.franchise.FranchiseService;
import com.accenture.infrastructure.persistence.dto.branch.BranchRequestDTO;
import com.accenture.infrastructure.persistence.dto.branch.BranchResponseDTO;
import com.accenture.infrastructure.persistence.dto.franchise.FranchiseRequestDTO;
import com.accenture.infrastructure.persistence.dto.franchise.FranchiseResponseDTO;
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
 * @name : FranchiseController.java
 * @date : 2024-12
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@RequiredArgsConstructor
@RequestMapping("/api/v1/franchises")
public @RestController class FranchiseController {

    private final FranchiseService franchiseService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<FranchiseResponseDTO>> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.franchiseService.findAll(pageable).join());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<FranchiseResponseDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.franchiseService.findById(id).join());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public HttpStatus save(@RequestBody FranchiseRequestDTO branchRequestDTO) {

        return this.franchiseService.save(branchRequestDTO).join();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public HttpStatus updateBranchName(@PathVariable(name = "id") Long id, @RequestParam String newBranchName) {
        return this.franchiseService.updateFranchiseName(id, newBranchName).join();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable(name = "id") Long id) {
        return this.franchiseService.delete(id).join();
    }
}
