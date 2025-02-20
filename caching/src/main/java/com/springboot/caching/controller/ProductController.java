package com.springboot.caching.controller;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.caching.model.Product;
import com.springboot.caching.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CacheManager cacheManager;
    // Create Product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    // Get Product by ID
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    //  Update Product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id); // Ensure correct ID is used
        return productService.updateProduct(product);
    }

    //  Delete Product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully!";
    }
    
    @GetMapping("/cache")
    public List<Object> getCachedProducts() {
        Cache cache = cacheManager.getCache("products");

        if (cache instanceof ConcurrentMapCache) {
            // Access the native map and stream over its values
            ConcurrentMap<Object, Object> nativeCache = ((ConcurrentMapCache) cache).getNativeCache();

            return nativeCache.values().stream()
                    .collect(Collectors.toList()); // Convert to List
        }

        return Collections.emptyList();
    }
}
