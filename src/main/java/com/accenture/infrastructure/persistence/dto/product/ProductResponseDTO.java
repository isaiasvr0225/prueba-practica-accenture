package com.accenture.infrastructure.persistence.dto.product;

import com.accenture.domain.entity.Branch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductResponseDTO(

        @NotNull
        Long id,

        @NotNull
        @NotBlank
        String name,

        @NotNull
        Integer stock,

        @NotNull
        Branch branch
) {
}
