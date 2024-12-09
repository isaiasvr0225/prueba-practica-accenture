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
     * @return CompletableFuture<Page<FranchiseResponseDTO>>
     */
    CompletableFuture<Page<FranchiseResponseDTO>> findAll(Pageable pageable);

    /**
     * This method is used to find a franchise by id
     * @param id id
     * @return CompletableFuture<FranchiseResponseDTO>
     */
    CompletableFuture<FranchiseResponseDTO> findById(Long id);

    /**
     * This method is used to save a franchise
     * @param franchiseRequestDTO franchiseRequestDTO
     * @return CompletableFuture<FranchiseRequestDTO>
     */
    CompletableFuture<HttpStatus> save(FranchiseRequestDTO franchiseRequestDTO);

    /**
     * This method is used to update a franchise name
     * @param id id
     * @param newFranchiseName newBranchName
     * @return CompletableFuture<HttpStatus>
     */
    CompletableFuture<HttpStatus> updateFranchiseName(Long id, String newFranchiseName);

    /**
     * This method is used to delete a franchise
     * @param id id
     * @return CompletableFuture<HttpStatus>
     */
    CompletableFuture<HttpStatus> delete(Long id);

}
