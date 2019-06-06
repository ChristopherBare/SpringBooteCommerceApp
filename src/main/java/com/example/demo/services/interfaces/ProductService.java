package com.example.demo.services.interfaces;

import com.example.demo.model.Product;

import java.util.List;

/**
 * Interfaces are used for Inversion of Control.
 */
public interface ProductService {
    Product getProductById(int id);
    Product saveProduct(Product product);
    List<Product> getAllProducts();
    void deleteProduct(Product product);
    void deleteProductById(int id);
}
