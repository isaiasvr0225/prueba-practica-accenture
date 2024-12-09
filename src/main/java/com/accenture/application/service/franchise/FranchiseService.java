package com.accenture.application.service.franchise;

import com.accenture.infrastructure.persistence.dto.branch.BranchRequestDTO;
import com.accenture.infrastructure.persistence.dto.branch.BranchResponseDTO;
import com.accenture.infrastructure.persistence.dto.franchise.FranchiseRequestDTO;
import com.accenture.infrastructure.persistence.dto.franchise.FranchiseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.concurrent.CompletableFuture;

/**
 * @package : com.accenture.application.service
 * @name : FranchiseService.java
 * @date : 2024-12
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
public interface FranchiseService {

    /**
     * This method is used to find all franchises using pagination
     * @param pageable pageable
     * @return CompletableFuture<Page<BranchResponseDTO>>
     */
    CompletableFuture<Page<FranchiseResponseDTO>> findAll(Pageable pageable);

    /**
     * This method is used to find a branch by id
     * @param id id
     * @return CompletableFuture<BranchResponseDTO>
     */
    CompletableFuture<FranchiseResponseDTO> findById(Long id);

    /**
     * This method is used to save a branch
     * @param branchRequestDTO branchRequestDTO
     * @return CompletableFuture<BranchRequestDTO>
     */
    CompletableFuture<HttpStatus> save(FranchiseRequestDTO branchRequestDTO);

    /**
     * This method is used to update a branch name
     * @param id id
     * @param newBranchName newBranchName
     * @return CompletableFuture<UserRequestDTO>
     */
    CompletableFuture<HttpStatus> updateFranchiseName(Long id, String newBranchName);

    /**
     * This method is used to delete a branch
     * @param id id
     * @return CompletableFuture<Void>
     */
    CompletableFuture<HttpStatus> delete(Long id);

}
