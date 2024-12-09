package com.accenture.infrastructure.persistence.dto.branch;

import com.accenture.domain.entity.Franchise;
import com.accenture.domain.entity.Product;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record BranchResponseDTO(

        @NotNull
        Long id,

        @NotBlank
        String name,

        @Email
        @NotBlank
        Franchise franchise,

        @NotNull
        List<Product> products
) {
}
