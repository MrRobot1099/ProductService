package com.project.productservice.services;

import com.project.productservice.model.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(long id);
    public List<Product> getAllProducts();
    public Product createProduct(Product product);
    public Product replaceProduct(Product product);
    public Product editProduct(Product product);
    public void deleteProduct(long id);
}
