package com.project.productservice.services;

import com.project.productservice.dtos.ProductDTO;
import com.project.productservice.dtos.UpdateDescriptionOnlyDTO;
import com.project.productservice.model.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(long id);
    public List<Product> getAllProducts();
    public Product createProduct(ProductDTO productDTO);
    public Product replaceProduct(long id, ProductDTO productDTO);
    public Product editProduct(long id, UpdateDescriptionOnlyDTO updateDescriptionOnlyDTO);
    public void deleteProduct(long id);
}
