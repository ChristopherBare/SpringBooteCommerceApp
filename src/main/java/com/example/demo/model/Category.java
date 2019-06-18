package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Product product) {
        this.name = name;
        this.products.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
