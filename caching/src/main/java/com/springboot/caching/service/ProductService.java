package com.springboot.caching.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springboot.caching.model.Product;
import com.springboot.caching.repo.ProductRepository;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //Fetch product by ID and cache it
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        System.out.println("Fetching from Database...");
        return productRepository.findById(id).orElse(null);
    }

    // Update product and refresh the cache
    @CachePut(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // Delete product and remove it from the cache
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
