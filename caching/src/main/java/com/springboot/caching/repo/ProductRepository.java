package com.springboot.caching.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.caching.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
