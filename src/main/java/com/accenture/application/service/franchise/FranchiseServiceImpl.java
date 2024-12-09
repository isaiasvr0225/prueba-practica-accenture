package com.accenture.application.service.franchise;

import com.accenture.domain.entity.Branch;
import com.accenture.domain.entity.Franchise;
import com.accenture.domain.exception.BranchException;
import com.accenture.domain.exception.FranchiseException;
import com.accenture.domain.exception.UserException;
import com.accenture.infrastructure.persistence.dto.franchise.FranchiseRequestDTO;
import com.accenture.infrastructure.persistence.dto.franchise.FranchiseResponseDTO;
import com.accenture.infrastructure.persistence.repository.BranchRepository;
import com.accenture.infrastructure.persistence.repository.FranchiseRepository;
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
public @Service class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;

    private final Logger logger = LoggerFactory.getLogger(FranchiseServiceImpl.class);


    /**
     * This method is used to find all franchises using pagination, also it is an asynchronous method
     * @param pageable pageable
     * @return CompletableFuture<Page<FranchiseResponseDTO>>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Page<FranchiseResponseDTO>> findAll(Pageable pageable) {

        Page<Franchise> pageFranchiseEntity = this.franchiseRepository.findAll(pageable);

        logger.info("pageFranchiseEntity: " + pageFranchiseEntity);

        if (pageFranchiseEntity.isEmpty()) {
            logger.error("Internal server error");
            throw new FranchiseException("500", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Page<FranchiseResponseDTO> franchiseResponseDTOS = pageFranchiseEntity.map((branchEntity) -> FranchiseResponseDTO.builder()
                .id(branchEntity.getId())
                .name(branchEntity.getName())
                .branches(branchEntity.getBranches())
                .build());

        logger.info("franchiseResponseDTOS: " + franchiseResponseDTOS);

        return CompletableFuture.completedFuture(franchiseResponseDTOS);
    }

    /**
     * This method is used to find a franchise by id, also it is an asynchronous method
     * @param id id
     * @return CompletableFuture<FranchiseResponseDTO>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<FranchiseResponseDTO> findById(Long id) {

        logger.info("id: " + id);

        Franchise franchiseEntity = this.franchiseRepository.findById(id)
                .orElseThrow(() -> new FranchiseException("404", "Franchise not found", HttpStatus.NOT_FOUND));

        logger.info("franchiseEntity: " + franchiseEntity);

        FranchiseResponseDTO franchiseResponseDTO = FranchiseResponseDTO.builder()
                .id(franchiseEntity.getId())
                .name(franchiseEntity.getName())
                .branches(franchiseEntity.getBranches())
                .build();

        logger.info("franchiseResponseDTO: " + franchiseResponseDTO);

        return CompletableFuture.completedFuture(franchiseResponseDTO);

    }

    /**
     * This method is used to save a new franchise, also it is an asynchronous method
     * @param franchiseRequestDTO franchiseRequestDTO
     * @return CompletableFuture<HttpStatus>
     */
    @Override
    public CompletableFuture<HttpStatus> save(FranchiseRequestDTO franchiseRequestDTO) {

        Franchise franchiseEntity = Franchise.builder()
                .name(franchiseRequestDTO.name())
                .branches(franchiseRequestDTO.branches())
                .build();

        this.franchiseRepository.save(franchiseEntity);
        return CompletableFuture.completedFuture(HttpStatus.CREATED);
    }


    /**
     * This method is used to update a franchise name, also it is an asynchronous method
     * @param id id
     * @param newBranchName newBranchName
     * @return CompletableFuture<HttpStatus>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<HttpStatus> updateFranchiseName(Long id, String newBranchName) {


        Franchise franchiseEntityFound = this.franchiseRepository.findById(id)
                .orElseThrow(() -> new FranchiseException("404", "Franchise not found", HttpStatus.NOT_FOUND));

        logger.info("franchiseEntityFound: " + franchiseEntityFound);

        this.franchiseRepository.save(Franchise.builder()
                .id(id)
                .name(newBranchName)
                .build());
        return CompletableFuture.completedFuture(HttpStatus.OK);
    }

    /**
     * This method is used to delete a franchise by its id, also it is an asynchronous method
     * @param id id
     * @return CompletableFuture<Void>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<HttpStatus> delete(Long id) {

        if (id == null) {
            logger.error("Franchise id must not be null");
            throw new UserException("400", "Branch id must not be null", HttpStatus.BAD_REQUEST);
        }

        this.franchiseRepository.deleteById(id);

        return CompletableFuture.completedFuture(HttpStatus.OK);
    }


}
