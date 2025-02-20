package com.springboot.pagination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.springboot.pagination.model.Product;
import com.springboot.pagination.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Add one product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Add multiple products
    public List<Product> addMultipleProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    // Update product
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setCategory(updatedProduct.getCategory());
            return productRepository.save(product);
        }
        return null;
    }

    // Delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Custom paginated products with sort, filter, and search
    public Page<Product> getCustomPaginatedProducts(int page, int size, String sortBy, String sortDir, String category, String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if (category != null && keyword != null) {
            return productRepository.findByCategoryAndNameContainingIgnoreCase(category, keyword, pageable);
        } else if (category != null) {
            return productRepository.findByCategory(category, pageable);
        } else if (keyword != null) {
            return productRepository.findByNameContainingIgnoreCase(keyword, pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }
    
    public Page<Product> getProductsByPriceRange(int page, int size, double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice, PageRequest.of(page, size));
    }

}
