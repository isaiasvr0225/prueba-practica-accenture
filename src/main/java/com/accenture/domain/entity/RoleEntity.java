package com.accenture.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public @Entity class RoleEntity {

    @Id
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true, name = "rol_name")
    private RoleEnum roleName;
}
