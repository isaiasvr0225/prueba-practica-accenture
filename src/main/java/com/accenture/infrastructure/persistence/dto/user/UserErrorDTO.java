package com.accenture.infrastructure.persistence.dto.user;

import lombok.Builder;

import java.io.Serializable;

/**
 * This is a DTO class for Client error handling
 * @package : com.sprayl.infrastructure.persistence.dto
 * @name : UserErrorDTO.java
 * @date : 2024-08
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@Builder
public record UserErrorDTO(
        String code,
        String message
) implements Serializable {}
