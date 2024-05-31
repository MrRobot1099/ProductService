package com.project.productservice.controller;

import com.project.productservice.commons.AuthenticationCommons;
import com.project.productservice.dtos.ProductDTO;
import com.project.productservice.dtos.Roles;
import com.project.productservice.dtos.UpdateDescriptionOnlyDTO;
import com.project.productservice.dtos.UserDTO;
import com.project.productservice.exceptions.CategoryNotFoundException;
import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.model.Product;
import com.project.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private AuthenticationCommons authenticationCommons;

    ProductController(@Qualifier(value = "selfProductService") ProductService productService, AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }


    @GetMapping("/{id}/{token}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id, @PathVariable String token) throws ProductNotFoundException {
        UserDTO user = authenticationCommons.validateToken(token);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(@RequestParam("pageNumber") int pageNumber,
                                                           @RequestParam("pageSize") int pageSize,
                                                           @RequestParam("sortDir") String sortDir) {
        /*UserDTO user = authenticationCommons.validateToken(token);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }*/


        return new ResponseEntity<>(productService.getAllProducts(pageNumber, pageSize, sortDir), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws CategoryNotFoundException {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    // Took request body as productDTO because on the fakeStore api the category is in string
    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException, CategoryNotFoundException {
        return new ResponseEntity<>(productService.replaceProduct(id, product),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product dataToUpdate) throws CategoryNotFoundException, ProductNotFoundException {
        return new ResponseEntity<>(productService.updateProduct(id, dataToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

}
