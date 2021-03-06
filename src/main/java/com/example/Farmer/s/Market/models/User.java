package com.example.Farmer.s.Market.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=10)
    private String name;



    @NotNull(message = "Password must not be null.")
    @Size(min=3, max=10, message = "Password must be between 3 and 10 characters")
    private String password;

    public User() {}

    public int getId() {
        return id;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }




    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {

        return name;
    }



    public String getPassword() {
        return password;
    }


}


