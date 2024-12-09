package com.accenture.infrastructure.persistence.dto.branch;

import com.accenture.domain.entity.Product;
import com.accenture.domain.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link UserEntity}
 */
public record BranchRequestDTO(

        @NotNull
        @NotBlank
        String name,

        @NotNull
        Long franchiseId,

        @NotNull
        List<Product> products
        ) implements Serializable {
}