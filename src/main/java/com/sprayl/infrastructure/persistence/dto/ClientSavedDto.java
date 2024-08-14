package com.sprayl.infrastructure.persistence.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ClientSavedDto(

        @NotNull
        Long id,

        @Email
        @NotBlank
        String fullName,

        @NotBlank
        String email
) {
}
