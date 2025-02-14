package com.example.ols_backend_spring.coursematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ols_backend_spring.coursematerial.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}