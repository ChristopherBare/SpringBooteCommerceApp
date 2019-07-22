package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    /** Reference to Product Repository */
    private final ProductRepository productRepository;

    /** Initializes repository reference */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public Product findById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    public List<Product> findByBrandAndOrCategory(String brand, String category) {
        if(category == null && brand == null) {
            return productRepository.findAll();
        } else if(category == null) {
            return productRepository.findByBrand(brand);
        } else if(brand == null) {
            return  productRepository.findByCategory(category);
        } else {
            return productRepository.findByBrandAndCategory(brand, category);
        }
    }
    
    public List<String> findDistinctBrands() {
        return productRepository.findDistinctBrands();
    }
    
    public List<String> findDistinctCategories() {
        return productRepository.findDistinctCategories();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }
}
