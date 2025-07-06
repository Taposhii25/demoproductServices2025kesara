package com.scaler.demoproductservices2025kesara.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class FakeStoreProductdto {
    private Long id ;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

}
