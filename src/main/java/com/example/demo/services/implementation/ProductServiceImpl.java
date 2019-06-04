package com.example.demo.services.implementation;

import com.example.demo.model.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementations are the realization of the service interfaces.
 */
@Service
public class ProductServiceImpl implements ProductService {

    // Reference to Product Repository
    private final ProductRepository productRepository;

    // Initializes repository reference
    public ProductServiceImpl(ProductRepository productRepository) { this.productRepository = productRepository; }


    // ===== Examples of service method implementations ===== //
    @Override
    public Product getProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
