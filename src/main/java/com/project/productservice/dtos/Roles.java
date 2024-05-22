package com.project.productservice.dtos;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Roles {
    private String name;
    private String description;
}
