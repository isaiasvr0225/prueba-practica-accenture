package com.accenture.infrastructure.persistence.repository;

import com.accenture.domain.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findRoleEntityById(Long id);
}