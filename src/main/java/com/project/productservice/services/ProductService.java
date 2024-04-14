package com.project.productservice.services;

import com.project.productservice.dtos.ProductDTO;
import com.project.productservice.exceptions.CategoryNotFoundException;
import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public ProductDTO getProductById(Long id) throws ProductNotFoundException;
    public List<ProductDTO> getAllProducts();
    public Product createProduct(Product product) throws CategoryNotFoundException;
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException, CategoryNotFoundException;
    public Product updateProduct(Long id, Product dateToUpdate) throws CategoryNotFoundException, ProductNotFoundException;
    public void deleteProduct(Long id);
}
