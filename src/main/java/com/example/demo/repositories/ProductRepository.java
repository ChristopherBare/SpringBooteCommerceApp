package com.example.demo.repositories;

import com.example.demo.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

// https://www.baeldung.com/spring-data-jpa-query
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findById(int id);
    void deleteById(int id);
    List<Product> findAll();
}
