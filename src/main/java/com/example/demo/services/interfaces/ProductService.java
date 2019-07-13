package com.example.demo.services.interfaces;

import com.example.demo.model.Product;

import java.util.List;

/**
 * Interfaces are used for Inversion of Control.
 */
public interface ProductService {
    Product findById(int id);
    Product save(Product product);
    List<Product> findAll();
    void delete(Product product);
    void deleteById(int id);
}
