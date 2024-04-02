package com.project.productservice.services;

import com.project.productservice.dtos.ProductDTO;
import com.project.productservice.dtos.UpdateDescriptionOnlyDTO;
import com.project.productservice.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public Product getProductById(Long id);
    public List<Product> getAllProducts();
    public Product createProduct(ProductDTO productDTO);
    public Product replaceProduct(Long id, ProductDTO productDTO);
    public Product editProduct(Long id, Map<String, String> dataToUpdate);
    public void deleteProduct(Long id);
}
