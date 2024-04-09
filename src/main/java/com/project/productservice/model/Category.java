package com.project.productservice.model;

import ch.qos.logback.core.model.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    String categoryName;
}
