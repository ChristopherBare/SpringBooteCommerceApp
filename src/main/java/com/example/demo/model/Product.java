package com.example.demo.model;

import lombok.*;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "categoryId", foreignKey = @ForeignKey(name = "FK_CategoryId"))
    private Category category;
    private double price;
    private int quantity, rating;
    private String description, name, brand, image;

    public Product(double price, int quantity, String description, String name,
                   String brand, int rating, String image, String categoryName) {
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.name = name;
        this.brand = brand;
        this.rating = rating;
        this.image = image;
        this.category = new Category(categoryName);
    }
}


