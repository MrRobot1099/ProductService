package com.project.productservice.services;

import com.project.productservice.dtos.ProductDTO;
import com.project.productservice.dtos.UpdateDescriptionOnlyDTO;
import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.model.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long id) throws ProductNotFoundException;
    public List<Product> getAllProducts();
    public Product createProduct(ProductDTO productDTO);
    public Product replaceProduct(Long id, ProductDTO productDTO);
    public Product editProduct(Long id, UpdateDescriptionOnlyDTO updateDescriptionOnlyDTO);
    public void deleteProduct(Long id);
}
