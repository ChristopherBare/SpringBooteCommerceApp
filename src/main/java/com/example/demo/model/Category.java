package com.example.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int categoryId;
    String name;

    public Category(String name) {
        this.name = name;
    }

}
