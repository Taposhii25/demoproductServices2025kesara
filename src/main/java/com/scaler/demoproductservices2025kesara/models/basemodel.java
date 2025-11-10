package com.scaler.demoproductservices2025kesara.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class basemodel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto generated
    private Long id;


}
