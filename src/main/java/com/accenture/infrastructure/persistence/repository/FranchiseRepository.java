package com.accenture.infrastructure.persistence.repository;

import com.accenture.domain.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}