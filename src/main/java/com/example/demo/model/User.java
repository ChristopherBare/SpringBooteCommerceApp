package com.example.demo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class User {
    String email, encryptedPassword, resetPasswordToken, currentSignInIP, LastSignInIP, role, name, address, city, zip, state;
    DateTimeFormat lastSignInAt, currentSignInAt, rememberCreatedAt;
    Date birthday;

    public User(String email, String encryptedPassword) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
    }


}


