package com.project.productservice.inheritancerepresentation.singletable;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity(name = "st_user")
// All the parent and child class field will be in one single class
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
