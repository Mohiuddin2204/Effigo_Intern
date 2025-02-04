package com.springboot.manytoone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.manytoone.model.Country;

@Repository
public interface CountryJpaRepository extends JpaRepository<Country, Integer> {

}