package com.project.productservice.repository;

import com.project.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, Long> {

    Category findByCategoryNameIgnoreCase(String categoryName);
    Optional<Category> findById(Long aLong);
    Category save(Category category);
}