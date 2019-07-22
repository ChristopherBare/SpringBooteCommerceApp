package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// https://www.baeldung.com/spring-data-jpa-query
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findById(long id);
    List<Product> findByBrand(String brand);
    List<Product> findByCategory(String category);
    List<Product> findByBrandAndCategory(String brand, String category);
    List<Product> findAll();
    
    @Query("SELECT DISTINCT p.brand FROM Product p")
    List<String> findDistinctBrands();
    
    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findDistinctCategories();
}
