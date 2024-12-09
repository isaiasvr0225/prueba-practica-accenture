package com.accenture.infrastructure.persistence.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserResponseDTO(

        @NotNull
        Long id,

        @Email
        @NotBlank
        String fullName,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String role
) {
}
