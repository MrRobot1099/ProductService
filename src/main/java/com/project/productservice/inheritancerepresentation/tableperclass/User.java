package com.project.productservice.inheritancerepresentation.tableperclass;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "tpc_user")
// here the parent class object will be created and it is same as MSC but the diff is the user table also created
// table for parent and child class will be created
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
