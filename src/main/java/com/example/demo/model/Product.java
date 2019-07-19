package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private double price;
    private int quantity, rating;
    private String description, name, brand, category, image;

    public Product(double price, int quantity, String description, String name,
                   String brand, int rating, String image, String category) {
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.name = name;
        this.brand = brand;
        this.rating = rating;
        this.image = image;
        this.category = category;
    }
}


