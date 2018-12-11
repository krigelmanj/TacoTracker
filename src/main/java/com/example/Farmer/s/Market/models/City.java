package com.example.Farmer.s.Market.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/*
@Entity
*/
public class City {

    @NotNull
    @Size(min=3, max=10)
    private String name;



    public City() {}


    public City(String name) {
        this.name = name;
    }




    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }



}


