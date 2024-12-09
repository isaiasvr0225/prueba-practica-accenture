package com.accenture.application.service.product;

import com.accenture.domain.entity.UserEntity;
import com.accenture.domain.exception.UserException;
import com.accenture.infrastructure.persistence.dto.user.UserRequestDTO;
import com.accenture.infrastructure.persistence.dto.user.UserResponseDTO;
import com.accenture.infrastructure.persistence.repository.RoleEntityRepository;
import com.accenture.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @package : com.sprayl.application.service
 * @name : UserServiceImpl.java
 * @date : 2024-08
 * @author  : Isaias Villarreal
 * @version : 1.0.0
 */

@RequiredArgsConstructor
public @Service class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;

    private final RoleEntityRepository roleRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    /**
     * This method is used to find all clients using pagination, also it is an asynchronous method
     * @param Pageable pageable
     * @return CompletableFuture<Page<UserRequestDTO>>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Page<UserResponseDTO>> findAll(Pageable pageable) {

        Page<UserEntity> pageClientEntity = this.userRepository.findAll(pageable);

        logger.info("pageClientEntity: " + pageClientEntity);

        if (pageClientEntity.isEmpty()) {
            logger.error("Internal server error");
            throw new UserException("500", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Page<UserResponseDTO> clientSavedDtos = pageClientEntity.map((userEntity) -> UserResponseDTO.builder()
                .id(userEntity.getId())
                .fullName(userEntity.getFullName())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .build());

        logger.info("clientSavedDtos: " + clientSavedDtos);

        return CompletableFuture.completedFuture(clientSavedDtos);
    }

    /**
     * This method is used to find a client by id and cache, also it is an asynchronous method
     * @param Long id
     * @return CompletableFuture<UserRequestDTO>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<UserResponseDTO> findById(Long id) {

        logger.info("id: " + id);

        UserEntity userEntity = this.userRepository.findById(id)
                .orElseThrow(() -> new UserException("404", "Client not found", HttpStatus.NOT_FOUND));

        logger.info("userEntity: " + userEntity);

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(userEntity.getId())
                .fullName(userEntity.getFullName())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .build();

        logger.info("userResponseDTO: " + userResponseDTO);

        return CompletableFuture.completedFuture(userResponseDTO);

    }

    /**
     * This method is used to save a client, also it is an asynchronous method
     * @param ClientDto userRequestDTO
     * @return CompletableFuture<HttpStatus>
     */
    @Override
    public CompletableFuture<HttpStatus> save(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null              ||
            userRequestDTO.fullName().isBlank() ||
            userRequestDTO.email().isBlank()    ||
            userRequestDTO.password().isBlank()){
            logger.error("UserRequestDTO must not be null");
            throw new UserException("400", "UserRequestDTO must not be null", HttpStatus.BAD_REQUEST);
        }

        var userRole = this.roleRepository.findRoleEntityById(2L);

        UserEntity userEntity = UserEntity.builder()
                .fullName(userRequestDTO.fullName())
                .email(userRequestDTO.email())
                .password(new BCryptPasswordEncoder().encode(userRequestDTO.password()))
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialsNoExpired(true)
                .role(userRole.getRoleName().name())
                .build();

        this.userRepository.save(userEntity);
        return CompletableFuture.completedFuture(HttpStatus.CREATED);
    }


    /**
     * This method is used to update a client, also it is an asynchronous method
     * @param Long id
     * @param ClientDto userRequestDTO
     * @return CompletableFuture<UserRequestDTO>
     */
    @Async("asyncExecutor")
    @Override
    public CompletableFuture<HttpStatus> update(Long id, UserRequestDTO userRequestDTO) {

        if (    id == null ||
                userRequestDTO == null ||
                userRequestDTO.email().isBlank() ||
                userRequestDTO.password().isBlank()
        ){

            logger.error("CC and fields must not be null or blank");
            throw new UserException("400", "Id and fields must not be null or blank", HttpStatus.BAD_REQUEST);

        }

        UserEntity userEntityFound = this.userRepository.findById(id)
                .orElseThrow(() -> new UserException("404", "Client not found", HttpStatus.NOT_FOUND));

        logger.info("userEntityFound: " + userEntityFound);

        this.userRepository.save(UserEntity.builder()
                .id(userRequestDTO.id())
                .fullName(userRequestDTO.fullName())
                .password(new BCryptPasswordEncoder().encode(userRequestDTO.password()))
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
            throw new UserException("400", "CC must not be null", HttpStatus.BAD_REQUEST);
        }

        this.userRepository.deleteById(id);

        return CompletableFuture.completedFuture(HttpStatus.NO_CONTENT);
    }


}
