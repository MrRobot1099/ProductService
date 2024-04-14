package com.project.productservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel{

    private String title;
    private double price;
    // By default, the fetch type is Eager in Many to One as we fetch single record every time
    // we can change the fetch type per our requirement.
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JsonBackReference
    private Category category;
    private String description;
    private String image;

}

/*
  1     ->      1
Product ---- Category  ===> M : 1
  M      <-     1
-------------------------
  M             1


  1     ->     M
Movie ------- Actor ====> M:M
  M      <-    1
-----------------------
   M           M

*/
