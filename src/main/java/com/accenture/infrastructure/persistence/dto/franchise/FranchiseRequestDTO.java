package com.accenture.infrastructure.persistence.dto.franchise;

import com.accenture.domain.entity.Branch;
import com.accenture.domain.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link UserEntity}
 */
public record FranchiseRequestDTO(

        @NotNull
        @NotBlank
        String name,

        @NotNull
        List<Branch> branches
        ) implements Serializable {
}