package com.scaler.demoproductservices2025kesara.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


@Getter
@Setter
@Entity(name = "products")
public class Product extends basemodel {

    private String title;
    private String description;
    private double price;
    private String imageurl;

//    @ManyToOne
//    private Category category;
//    @ManyToOne(cascade= CascadeType.PERSIST)
//    private Category category;
    @ManyToOne(cascade= CascadeType.REMOVE)
    private Category category;
}
