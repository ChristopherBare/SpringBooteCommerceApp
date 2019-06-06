package com.example.demo.model;

import com.example.demo.Util.Hash;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    String email, password, salt, role, name, address, city, zip, state;
    Date birthday;

    public User(String email, String password) throws NoSuchAlgorithmException {
        this.email = email;
        this.salt = Hash.getNewSalt();
        this.password = Hash.hashAndSalt(password, salt);
    }

    public User(String email, String password, String role, String name,
                String address, String city, String zip, String state,
                Date birthday) throws NoSuchAlgorithmException {
        this.email = email;
        this.salt = Hash.getNewSalt();
        this.password = Hash.hashAndSalt(password, salt);
        this.role = role;
        this.name = name;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.birthday = birthday;
    }

    public User(String email, String password, String name, String address, String city,
                String zip, String state, Date birthday) throws NoSuchAlgorithmException {
        this.email = email;
        this.salt = Hash.getNewSalt();
        this.password = Hash.hashAndSalt(password, salt);
        this.name = name;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.birthday = birthday;
    }

    public User(String email, String password, String role, String name) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        if (this.getSalt() == null) this.salt = Hash.getNewSalt();
        this.password = Hash.hashAndSalt(password, this.salt);
    }

    public void setEmail(String email) {
        this.setEmail(email);
    }
}


