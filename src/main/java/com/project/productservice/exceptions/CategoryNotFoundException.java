package com.project.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryNotFoundException extends Exception{
    private Long categoryId;
    public CategoryNotFoundException(Long categoryId,String message) {
        super(message);
        this.categoryId = categoryId;
    }
}
