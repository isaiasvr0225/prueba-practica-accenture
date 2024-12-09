package com.accenture.infrastructure.persistence.dto.franchise;

import com.accenture.domain.entity.Branch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record FranchiseResponseDTO(

        @NotNull
        Long id,

        @NotNull
        @NotBlank
        String name,

        @NotNull
        List<Branch> branches
) {
}
