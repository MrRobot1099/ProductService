package com.project.productservice.inheritancerepresentation.singletable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_instructor")
public class Instructor extends User {
    private String specialization;
}
