package com.project.productservice.services;

import com.project.productservice.dtos.FakeStoreProductDTO;
import com.project.productservice.dtos.ProductDTO;
import com.project.productservice.dtos.UpdateDescriptionOnlyDTO;
import com.project.productservice.exceptions.CategoryNotFoundException;
import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.model.Category;
import com.project.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;
    RedisTemplate redisTemplate;

    FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ProductDTO getProductById(Long id) throws ProductNotFoundException {

        System.out.println("Got the request in Product Service");
        // check if the product is present in the cache
        ProductDTO productDTO = (ProductDTO) redisTemplate.opsForHash().get("PRODUCT", "PRODUCT_" + id);
        if (Objects.nonNull(productDTO)) {
            return productDTO;
        }
        // call the rest template object and get the product by id
        ProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, ProductDTO.class);
        // Convert the productDTO to product
        if (fakeStoreProductDTO == null) {
            throw new ProductNotFoundException(id, "Invalid product id passed, Please retry with a valid product id");
        }
        // store the product in the cache
        redisTemplate.opsForHash().put("PRODUCT", "PRODUCT_" + id, fakeStoreProductDTO);

        return fakeStoreProductDTO;
    }

    @Override
    public Page<ProductDTO> getAllProducts(int pageNumber, int pageSize, String sortDir) {

        // using array of productDTO to get all the products from the fake store api
        // We are not using the list of productDTO because it is generic and in runtime it will not be able to convert the response to the list of productDTO
        ProductDTO[] fakeStoreProductDTOS = restTemplate.getForObject("https://fakestoreapi.com/products/", ProductDTO[].class);
        // Convert the productDTO to product
        if (fakeStoreProductDTOS == null) {
            return null;
        }
        /*List<Product> products = new ArrayList<>();
        for (ProductDTO fakeStoreProductDTO : fakeStoreProductDTOS) {
            products.add(convertDtoToProduct(fakeStoreProductDTO));
        }*/
        return new PageImpl<>(List.of(fakeStoreProductDTOS));
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException {
        FakeStoreProductDTO productDTO = convertProductToDTO(product);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.execute("https://fakestoreapi.com/products/", HttpMethod.POST, requestCallback, responseExtractor);
        return fakeStoreProductDTO == null ? null : convertDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDTO productDTO = convertProductToDTO(product);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return fakeStoreProductDTO == null ? null : convertDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public Product updateProduct(Long id, Product dataToUpdate) {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, dataToUpdate, FakeStoreProductDTO.class);
        if (fakeStoreProductDTO == null) {
            return null;
        }
        return convertDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
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

    public FakeStoreProductDTO convertProductToDTO(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImage());

        Category category = new Category();
        category.setCategoryName(product.getCategory().getCategoryName());
        fakeStoreProductDTO.setCategory(category.getCategoryName());
        return fakeStoreProductDTO;
    }

}
