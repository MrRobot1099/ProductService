package com.project.productservice.services;

import com.project.productservice.DTOs.FakeStoreProductDTO;
import com.project.productservice.config.RestTemplateConfig;
import com.project.productservice.model.Category;
import com.project.productservice.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) {
        // call the rest template object and get the product by id
       FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" +id, FakeStoreProductDTO.class);
       // Convert the productDTO to product
        if (fakeStoreProductDTO == null) {
            return null;
        }
        return convertDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {

        // using array of productDTO to get all the products from the fake store api
        // We are not using the list of productDTO because it is generic and in runtime it will not be able to convert the response to the list of productDTO
        FakeStoreProductDTO[] fakeStoreProductDTOS = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDTO[].class);
        // Convert the productDTO to product
        if (fakeStoreProductDTOS == null) {
            return null;
        }
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOS) {
            products.add(convertDtoToProduct(fakeStoreProductDTO));
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Product product) {
        return null;
    }

    @Override
    public Product editProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(long id) {

    }

    public Product convertDtoToProduct(FakeStoreProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());

        Category category = new Category();
        category.setCategoryName(productDTO.getCategory());
        product.setCategory(category);
        return product;
    }
}
