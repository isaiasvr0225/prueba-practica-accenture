package com.accenture.infrastructure.persistence.dto.user;

import com.accenture.domain.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link UserEntity}
 */
public record UserRequestDTO(

        @NotNull
        Long id,

        @NotNull
        @NotBlank
        String fullName,

        @NotNull
        @NotBlank
        @Email
        String email,

        @NotNull
        @NotBlank
        String password
        ) implements Serializable {
}