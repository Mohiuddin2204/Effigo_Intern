package com.springboot.onetoone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.onetoone.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}