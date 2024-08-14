package com.sprayl.application.service;

import com.sprayl.domain.entity.ClientEntity;
import com.sprayl.domain.entity.PermissionEntity;
import com.sprayl.domain.entity.RoleEntity;
import com.sprayl.domain.entity.RoleEnum;
import com.sprayl.domain.exception.ClientException;
import com.sprayl.infrastructure.persistence.dto.ClientDto;
import com.sprayl.infrastructure.persistence.dto.ClientSavedDto;
import com.sprayl.infrastructure.persistence.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @package : com.sprayl.application.service
 * @name : ClientServiceImpl.java
 * @date : 2024-08
 * @author  : Isaias Villarreal
 * @version : 1.0.0
 */

@RequiredArgsConstructor
public @Service class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);


    /**
     * This method is used to find all clients using pagination, also it is an asynchronous method
     * @param Pageable pageable
     * @return CompletableFuture<Page<ClientDto>>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Page<ClientSavedDto>> findAll(Pageable pageable) {

        Page<ClientEntity> pageClientEntity = this.clientRepository.findAll(pageable);

        logger.info("pageClientEntity: " + pageClientEntity);

        if (pageClientEntity.isEmpty()) {
            logger.error("Internal server error");
            throw new ClientException("500", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Page<ClientSavedDto> clientSavedDtos = pageClientEntity.map((clientEntity) -> ClientSavedDto.builder()
                .id(clientEntity.getId())
                .fullName(clientEntity.getUsername())
                .email(clientEntity.getEmail())
                .build());

        logger.info("clientSavedDtos: " + clientSavedDtos);

        return CompletableFuture.completedFuture(clientSavedDtos);
    }

    /**
     * This method is used to find a client by id and cache, also it is an asynchronous method
     * @param Long id
     * @return CompletableFuture<ClientDto>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<ClientSavedDto> findById(Long id) {

        logger.info("id: " + id);

        ClientEntity clientEntity = this.clientRepository.findById(id)
                .orElseThrow(() -> new ClientException("404", "Client not found", HttpStatus.NOT_FOUND));

        logger.info("clientEntity: " + clientEntity);

        ClientSavedDto clientSavedDto = ClientSavedDto.builder()
                .id(clientEntity.getId())
                .fullName(clientEntity.getUsername())
                .email(clientEntity.getEmail())
                .build();

        logger.info("clientSavedDto: " + clientSavedDto);

        return CompletableFuture.completedFuture(clientSavedDto);

    }

    /**
     * This method is used to save a client, also it is an asynchronous method
     * @param ClientDto clientDto
     * @return CompletableFuture<HttpStatus>
     */
    @Override
    public CompletableFuture<HttpStatus> save(ClientDto clientDto) {
        if (clientDto == null              ||
            clientDto.username().isBlank() ||
            clientDto.email().isBlank()    ||
            clientDto.password().isBlank()){
            logger.error("ClientDto must not be null");
            throw new ClientException("400", "ClientDto must not be null", HttpStatus.BAD_REQUEST);
        }

        var readPermission = PermissionEntity
                .builder()
                .name("READ")
                .build();
        var userRole = RoleEntity
                .builder()
                .roleEnum(RoleEnum.USER)
                .permissionSet(Set.of(readPermission))
                .build();

        ClientEntity clientEntity = ClientEntity.builder()
                .username(clientDto.username())
                .email(clientDto.email())
                .password(new BCryptPasswordEncoder().encode(clientDto.password()))
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialsNoExpired(true)
                .roles(Set.of(userRole))
                .build();

        this.clientRepository.save(clientEntity);
        return CompletableFuture.completedFuture(HttpStatus.CREATED);
    }


    /**
     * This method is used to update a client, also it is an asynchronous method
     * @param Long id
     * @param ClientDto clientDto
     * @return CompletableFuture<ClientDto>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<HttpStatus> update(Long id, ClientDto clientDto) {

        if (    id == null ||
                clientDto == null ||
                clientDto.username().isBlank() ||
                clientDto.email().isBlank() ||
                clientDto.password().isBlank()
        ){

            logger.error("CC and fields must not be null or blank");
            throw new ClientException("400", "Id and fields must not be null or blank", HttpStatus.BAD_REQUEST);

        }

        ClientEntity clientEntityFound = this.clientRepository.findById(id)
                .orElseThrow(() -> new ClientException("404", "Client not found", HttpStatus.NOT_FOUND));

        logger.info("clientEntityFound: " + clientEntityFound);

        this.clientRepository.save(ClientEntity.builder()
                .id(clientDto.id())
                .username(clientDto.username())
                .password(new BCryptPasswordEncoder().encode(clientDto.password()))
                .build());
        return CompletableFuture.completedFuture(HttpStatus.OK);
    }

    /**
     * This method is used to delete a client, also it is an asynchronous method
     * @param Long cedula
     * @return CompletableFuture<Void>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<HttpStatus> delete(Long id) {

        if (id == null) {
            logger.error("CC must not be null");
            throw new ClientException("400", "CC must not be null", HttpStatus.BAD_REQUEST);
        }

        this.clientRepository.deleteById(id);

        return CompletableFuture.completedFuture(HttpStatus.NO_CONTENT);
    }


}
