package com.accenture.infrastructure.persistence.dto.product;

import lombok.Builder;

import java.io.Serializable;

/**
 * This is a DTO class for Client error handling
 * @package : com.accenture.infrastructure.persistence.dto
 * @name : ProductErrorDTO.java
 * @date : 2024-08
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@Builder
public record ProductErrorDTO(
        String code,
        String message
) implements Serializable {}
