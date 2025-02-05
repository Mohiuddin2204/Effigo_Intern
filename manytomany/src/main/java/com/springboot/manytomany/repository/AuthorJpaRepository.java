package com.springboot.manytomany.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.manytomany.model.Author;


@Repository
public interface AuthorJpaRepository extends JpaRepository<Author,Integer>{
    
}