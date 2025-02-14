package com.example.ols_backend_spring.coursematerial.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ols_backend_spring.coursematerial.entity.Category;
import com.example.ols_backend_spring.coursematerial.service.CategoryService;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/auth/user/api/category", "/auth/admin/api/category"})
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/auth/admin/api/category/{catId}")
    public ResponseEntity<Category> getCatById(@PathVariable Long catId){
        Category category = categoryService.getCategoryById(catId);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/auth/admin/api/category")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    @DeleteMapping("/auth/admin/api/category/{catId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long catId){
        categoryService.deleteCategoryById(catId);
        return ResponseEntity.noContent().build();
    }
}