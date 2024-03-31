package com.project.productservice.services;

import com.project.productservice.dtos.FakeStoreProductDTO;
import com.project.productservice.dtos.ProductDTO;
import com.project.productservice.dtos.UpdateDescriptionOnlyDTO;
import com.project.productservice.model.Category;
import com.project.productservice.model.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
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
    public Product createProduct(ProductDTO productDTO) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO fakeStoreProductDTO =  restTemplate.execute("https://fakestoreapi.com/products/", HttpMethod.POST, requestCallback, responseExtractor);
        return fakeStoreProductDTO == null ? null : convertDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public Product replaceProduct(long id, ProductDTO productDTO) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO fakeStoreProductDTO =  restTemplate.execute("https://fakestoreapi.com/products/" +id, HttpMethod.PUT, requestCallback, responseExtractor);
        return fakeStoreProductDTO == null ? null : convertDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public Product editProduct(long id, UpdateDescriptionOnlyDTO updateDescriptionOnlyDTO) {
       FakeStoreProductDTO fakeStoreProductDTO =  restTemplate.patchForObject("https://fakestoreapi.com/products/" +id, updateDescriptionOnlyDTO, FakeStoreProductDTO.class);
        if (fakeStoreProductDTO == null) {
            return null;
        }
       return convertDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public void deleteProduct(long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" +id);
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

    public ProductDTO convertProductToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());

        Category category = new Category();
        category.setCategoryName(product.getCategory().toString());
        productDTO.setCategory(category.getCategoryName());
        return productDTO;
    }
}
