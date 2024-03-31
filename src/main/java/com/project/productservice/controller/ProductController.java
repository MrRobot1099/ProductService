package com.project.productservice.controller;

import com.project.productservice.dtos.ProductDTO;
import com.project.productservice.dtos.UpdateDescriptionOnlyDTO;
import com.project.productservice.model.Product;
import com.project.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") long id){
        return productService.getProductById(id);
//        return new Product();
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }

    // Took request body as productDTO because on the fakeStore api the category is in string
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody ProductDTO productDTO){
        return productService.replaceProduct(id, productDTO);
    }

    @PatchMapping("/{id}")
    public Product editProduct(@PathVariable("id") long id, @RequestBody UpdateDescriptionOnlyDTO updateDescriptionOnlyDTO){
        return productService.editProduct(id, updateDescriptionOnlyDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);
    }

}
