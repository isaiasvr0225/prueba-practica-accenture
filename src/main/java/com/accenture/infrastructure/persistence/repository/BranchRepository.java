package com.accenture.infrastructure.persistence.repository;

import com.accenture.domain.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}