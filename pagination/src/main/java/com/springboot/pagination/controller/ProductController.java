package com.springboot.pagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.springboot.pagination.model.Product;
import com.springboot.pagination.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Add one product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // Add multiple products
    @PostMapping("/add-multiple")
    public List<Product> addMultipleProducts(@RequestBody List<Product> products) {
        return productService.addMultipleProducts(products);
    }

    // Update product
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Delete product
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // Get paginated list with sorting, filtering & searching
    @GetMapping("/paginated")
    public Page<Product> getPaginatedProducts(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size,
                                              @RequestParam(defaultValue = "id") String sortBy,
                                              @RequestParam(defaultValue = "asc") String sortDir,
                                              @RequestParam(required = false) String category,
                                              @RequestParam(required = false) String keyword) {
        return productService.getCustomPaginatedProducts(page, size, sortBy, sortDir, category, keyword);
    }
    
 // Fetch paginated products with price range filter
    @GetMapping("/paginated/price-range")
    public Page<Product> getProductsByPriceRange(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size,
                                                 @RequestParam double minPrice,
                                                 @RequestParam double maxPrice) {
        return productService.getProductsByPriceRange(page, size, minPrice, maxPrice);
    }

    
}
