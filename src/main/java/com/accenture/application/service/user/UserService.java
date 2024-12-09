package com.accenture.application.service.user;

import com.accenture.infrastructure.persistence.dto.user.UserRequestDTO;
import com.accenture.infrastructure.persistence.dto.user.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.concurrent.CompletableFuture;

/**
 * @package : com.sprayl.application.service
 * @name : UserService.java
 * @date : 2024-08
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
public interface UserService {

    /**
     * This method is used to find all clients using pagination
     * @param pageable pa
     * @return CompletableFuture<Page<UserRequestDTO>>
     */
    CompletableFuture<Page<UserResponseDTO>> findAll(Pageable pageable);

    /**
     * This method is used to find a client by id
     * @param Long id
     * @return CompletableFuture<UserRequestDTO>
     */
    CompletableFuture<UserResponseDTO> findById(Long id);

    /**
     * This method is used to save a client
     * @param ClientDto userRequestDTO
     * @return CompletableFuture<UserRequestDTO>
     */
    CompletableFuture<HttpStatus> save(UserRequestDTO userRequestDTO);

    /**
     * This method is used to update a client
     * @param Long id
     * @param ClientDto userRequestDTO
     * @return CompletableFuture<UserRequestDTO>
     */
    CompletableFuture<HttpStatus> update(Long id, UserRequestDTO userRequestDTO);

    /**
     * This method is used to delete a client
     * @param Long id
     * @return CompletableFuture<Void>
     */
    CompletableFuture<HttpStatus> delete(Long id);

}
