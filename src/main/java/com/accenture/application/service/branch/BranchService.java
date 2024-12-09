package com.accenture.application.service.branch;

import com.accenture.infrastructure.persistence.dto.branch.BranchRequestDTO;
import com.accenture.infrastructure.persistence.dto.branch.BranchResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.concurrent.CompletableFuture;

/**
 * @package : com.accenture.application.service
 * @name : BranchService.java
 * @date : 2024-12
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
public interface BranchService {

    /**
     * This method is used to find all branches using pagination
     * @param pageable pageable
     * @return CompletableFuture<Page<BranchResponseDTO>>
     */
    CompletableFuture<Page<BranchResponseDTO>> findAll(Pageable pageable);

    /**
     * This method is used to find a branch by id
     * @param id id
     * @return CompletableFuture<BranchResponseDTO>
     */
    CompletableFuture<BranchResponseDTO> findById(Long id);

    /**
     * This method is used to save a branch
     * @param branchRequestDTO branchRequestDTO
     * @return CompletableFuture<BranchRequestDTO>
     */
    CompletableFuture<HttpStatus> save(BranchRequestDTO branchRequestDTO);

    /**
     * This method is used to update a branch name
     * @param id id
     * @param newBranchName newBranchName
     * @return CompletableFuture<HttpStatus>
     */
    CompletableFuture<HttpStatus> updateBranchName(Long id, String newBranchName);

    /**
     * This method is used to delete a branch
     * @param id id
     * @return CompletableFuture<HttpStatus>
     */
    CompletableFuture<HttpStatus> delete(Long id);

}
