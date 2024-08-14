package com.sprayl.infrastructure.persistence.repository;

import com.sprayl.domain.entity.ClientEntity;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface is used to manage the clients repository, it extends from JpaRepository interface
 * @package : com.sprayl.infrastructure.persistence.repository
 * @name : ClientRepository.java
 * @date : 2024-08
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findClientEntityByUsername(@NotEmpty String username);
}
