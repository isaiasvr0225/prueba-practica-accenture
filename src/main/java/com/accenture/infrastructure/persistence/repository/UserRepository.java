package com.accenture.infrastructure.persistence.repository;

import com.accenture.domain.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface is used to manage the clients repository, it extends from JpaRepository interface
 * @package : com.sprayl.infrastructure.persistence.repository
 * @name : UserRepository.java
 * @date : 2024-08
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findClientEntityByEmail(@NotEmpty String username);
}
