package com.accenture.infrastructure.persistence.dto.product;

import com.accenture.domain.entity.Branch;
import com.accenture.domain.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link UserEntity}
 */
public record ProductRequestDTO(

        @NotNull
        @NotBlank
        String name,

        @NotNull
        Integer stock,

        @NotNull
        Long branchId
        ) implements Serializable {
}