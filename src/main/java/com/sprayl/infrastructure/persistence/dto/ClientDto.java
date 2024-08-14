package com.sprayl.infrastructure.persistence.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.sprayl.domain.entity.ClientEntity}
 */
public record ClientDto(

        @NotNull
        Long id,
        @NotNull
        @NotBlank
        String username,
        @NotNull
        @NotBlank
        @Email
        String email,
        @NotNull
        @NotBlank
        String password
        ) implements Serializable {
}