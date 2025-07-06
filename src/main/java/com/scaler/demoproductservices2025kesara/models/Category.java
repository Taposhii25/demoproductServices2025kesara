package com.scaler.demoproductservices2025kesara.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity(name = "categories")

public class Category extends basemodel {

    @Column(unique = true)
    private String name;
//    @OneToMany(mappedBy = "category", cascade = jakarta.persistence.CascadeType.REMOVE)
//    private List<Product> products;
}
