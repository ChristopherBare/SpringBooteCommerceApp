package com.example.demo.model;


import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "categoryId")
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", price=" + price +
                ", quantity=" + quantity +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
