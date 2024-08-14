package com.sprayl.application.service;

import com.sprayl.infrastructure.persistence.dto.ClientDto;
import com.sprayl.infrastructure.persistence.dto.ClientSavedDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.concurrent.CompletableFuture;

/**
 * @package : com.sprayl.application.service
 * @name : ClientService.java
 * @date : 2024-08
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
public interface ClientService {

    /**
     * This method is used to find all clients using pagination
     * @param Pageable pageable
     * @return CompletableFuture<Page<ClientDto>>
     */
    CompletableFuture<Page<ClientSavedDto>> findAll(Pageable pageable);

    /**
     * This method is used to find a client by id
     * @param Long id
     * @return CompletableFuture<ClientDto>
     */
    CompletableFuture<ClientSavedDto> findById(Long id);

    /**
     * This method is used to save a client
     * @param ClientDto clientDto
     * @return CompletableFuture<ClientDto>
     */
    CompletableFuture<HttpStatus> save(ClientDto clientDto);

    /**
     * This method is used to update a client
     * @param Long id
     * @param ClientDto clientDto
     * @return CompletableFuture<ClientDto>
     */
    CompletableFuture<HttpStatus> update(Long id, ClientDto clientDto);

    /**
     * This method is used to delete a client
     * @param Long id
     * @return CompletableFuture<Void>
     */
    CompletableFuture<HttpStatus> delete(Long id);

}
