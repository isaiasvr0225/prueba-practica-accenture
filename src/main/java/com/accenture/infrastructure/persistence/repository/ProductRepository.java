package com.accenture.infrastructure.persistence.repository;

import com.accenture.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}