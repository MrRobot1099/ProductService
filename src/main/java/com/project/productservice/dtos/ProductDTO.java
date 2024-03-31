package com.project.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
