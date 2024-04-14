package com.project.productservice.model;

import ch.qos.logback.core.model.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity

public class Category extends BaseModel {

    // The mappedBy property is what we use to tell Hibernate which variable we are using to represent the parent class in our child class
    // "category" is we have defined in product
    // For OneToMany by default the fetch type is Lazy as we need to fetch List of product
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = {CascadeType.REMOVE})
    @JsonManagedReference
    private List<Product> productList;
    private String categoryName;

    @Override
    public String toString() {
        return categoryName;
    }
}
