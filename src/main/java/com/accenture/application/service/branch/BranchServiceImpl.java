package com.accenture.application.service.branch;

import com.accenture.domain.entity.Branch;
import com.accenture.domain.exception.BranchException;
import com.accenture.domain.exception.UserException;
import com.accenture.infrastructure.persistence.dto.branch.BranchRequestDTO;
import com.accenture.infrastructure.persistence.dto.branch.BranchResponseDTO;
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
public @Service class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    private final FranchiseRepository franchiseRepository;

    private final Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);


    /**
     * This method is used to find all branches using pagination, also it is an asynchronous method
     * @param pageable pageable
     * @return CompletableFuture<Page<BranchResponseDTO>>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Page<BranchResponseDTO>> findAll(Pageable pageable) {

        Page<Branch> pageBranchEntity = this.branchRepository.findAll(pageable);

        logger.info("pageBranchEntity: " + pageBranchEntity);

        if (pageBranchEntity.isEmpty()) {
            logger.error("Internal server error");
            throw new BranchException("500", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Page<BranchResponseDTO> branchResponseDTOS = pageBranchEntity.map((branchEntity) -> BranchResponseDTO.builder()
                .id(branchEntity.getId())
                .name(branchEntity.getName())
                .franchise(branchEntity.getFranchise())
                .products(branchEntity.getProducts())
                .build());

        logger.info("branchResponseDTOS: " + branchResponseDTOS);

        return CompletableFuture.completedFuture(branchResponseDTOS);
    }

    /**
     * This method is used to find a branch by id, also it is an asynchronous method
     * @param id id
     * @return CompletableFuture<BranchResponseDTO>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<BranchResponseDTO> findById(Long id) {

        logger.info("id: " + id);

        Branch branchEntity = this.branchRepository.findById(id)
                .orElseThrow(() -> new BranchException("404", "Branch not found", HttpStatus.NOT_FOUND));

        logger.info("branchEntity: " + branchEntity);

        BranchResponseDTO branchResponseDTO = BranchResponseDTO.builder()
                .id(branchEntity.getId())
                .name(branchEntity.getName())
                .franchise(branchEntity.getFranchise())
                .products(branchEntity.getProducts())
                .build();

        logger.info("branchResponseDTO: " + branchResponseDTO);

        return CompletableFuture.completedFuture(branchResponseDTO);

    }

    /**
     * This method is used to save a new branch, also it is an asynchronous method
     * @param branchRequestDTO branchRequestDTO
     * @return CompletableFuture<HttpStatus>
     */
    @Override
    public CompletableFuture<HttpStatus> save(BranchRequestDTO branchRequestDTO) {

        var franchise = this.franchiseRepository.findById(branchRequestDTO.franchiseId())
                .orElseThrow(() -> new BranchException("404", "Franchise not found", HttpStatus.NOT_FOUND));

        Branch branchEntity = Branch.builder()
                .name(branchRequestDTO.name())
                .franchise(franchise)
                .products(branchRequestDTO.products())
                .build();

        this.branchRepository.save(branchEntity);
        return CompletableFuture.completedFuture(HttpStatus.CREATED);
    }


    /**
     * This method is used to update a branch name, also it is an asynchronous method
     * @param id id
     * @param newBranchName newBranchName
     * @return CompletableFuture<UserRequestDTO>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<HttpStatus> updateBranchName(Long id, String newBranchName) {


        Branch branchEntityFound = this.branchRepository.findById(id)
                .orElseThrow(() -> new BranchException("404", "Branch not found", HttpStatus.NOT_FOUND));

        logger.info("branchEntityFound: " + branchEntityFound);

        this.branchRepository.save(Branch.builder()
                .id(id)
                .name(newBranchName)
                .build());
        return CompletableFuture.completedFuture(HttpStatus.OK);
    }

    /**
     * This method is used to delete a branch by its id, also it is an asynchronous method
     * @param id id
     * @return CompletableFuture<Void>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<HttpStatus> delete(Long id) {

        if (id == null) {
            logger.error("Branch id must not be null");
            throw new UserException("400", "Branch id must not be null", HttpStatus.BAD_REQUEST);
        }

        this.branchRepository.deleteById(id);

        return CompletableFuture.completedFuture(HttpStatus.OK);
    }


}
