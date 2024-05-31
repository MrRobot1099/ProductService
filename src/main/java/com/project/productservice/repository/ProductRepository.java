package com.project.productservice.repository;

import com.project.productservice.model.Product;
import com.project.productservice.repository.projections.ProductWithIdAndTitle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

    Page<Product> findAll(Pageable pageable);

    //This method will return a Product with only Id and Title.
    @Query("select p.id as id, p.title as title from Product p where p.price > 120000 and lower(p.title) like '%pro%'")
    List<ProductWithIdAndTitle> someRandomQuery();

    @Query("select p.id as id, p.title as title from  Product p where p.id = :id")
    ProductWithIdAndTitle doSomething(@Param("id") Long id);


    //How many DB calls -> 2
    // First select the Product object & then fetching the Category object.
    @Query(value = "select * from product p where p.id = 2",  nativeQuery = true)
    Product doSomethingSQL();
}
