package com.example.demo.repositories;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

// https://www.baeldung.com/spring-data-jpa-query
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findProductById(int id);
    void deleteProductById(int id);
    @Query(value = "SELECT p FROM Product p")
    Collection<Product> findAllProductsWithCategory(Sort sort);

}
