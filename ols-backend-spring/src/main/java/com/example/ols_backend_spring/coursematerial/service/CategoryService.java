package com.example.ols_backend_spring.coursematerial.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ols_backend_spring.coursematerial.entity.Category;
import com.example.ols_backend_spring.coursematerial.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    // list all courses
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    // get course by its id
    public Category getCategoryById(Long catId){
        return categoryRepository.findById(catId)
                .orElseThrow(()-> new RuntimeException("Course not found by Id") );
    }

    // create a category
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    // delete a category by its id
    public void deleteCategoryById(Long catId){
        categoryRepository.deleteById(catId);
    }
}