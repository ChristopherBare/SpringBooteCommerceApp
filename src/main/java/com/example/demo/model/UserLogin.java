package com.example.demo.model;

import com.example.demo.Util.Hash;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login")
//@IdClass(User.class)
public class UserLogin implements Serializable {

//    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    private int user_id;

    private @NotNull @Column(unique=true) String email;
    private String password, salt;

    public UserLogin(User user) {
        this.user = user;
        this.user_id = user.getId();
        this.email = "";
        this.salt = Hash.getNewSalt();
        this.password = salt;
    }

    public UserLogin(User user, String email, String password) throws NoSuchAlgorithmException {
        this.user = user;
        this.user_id = user.getId();
        this.email = email;
        this.salt = Hash.getNewSalt();
        this.password = Hash.hashAndSalt(password, salt);
    }


    public void setPassword(String password) throws NoSuchAlgorithmException {
        if (this.getSalt() == null) this.salt = Hash.getNewSalt();
        this.password = Hash.hashAndSalt(password, this.salt);
    }

    public void setEmail(String email) {
        this.user.setEmail(email);
    }

}
