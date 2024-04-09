package com.project.productservice.inheritancerepresentation.mappedsuperclass;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
// User object will not be created, these field will be created in the child table extending this user class
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
