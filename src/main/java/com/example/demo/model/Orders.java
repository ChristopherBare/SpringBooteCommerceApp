package com.example.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@Entity
@Data
public class Orders {

    @Id private String Id;

}
