package com.project.productservice;

import com.project.productservice.model.Category;
import com.project.productservice.model.Product;
import com.project.productservice.repository.CategoryRepository;
import com.project.productservice.repository.ProductRepository;
import com.project.productservice.repository.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

//@SpringBootTest
class ProductServiceApplicationTests {


//    @Autowired // Dependency Injection
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void testQueries() {
////        List<ProductWithIdAndTitle> products = productRepository.someRandomQuery();
////
////        for (ProductWithIdAndTitle product : products) {
////            System.out.println(product.getId());
////            System.out.println(product.getTitle());
////        }
//
////        ProductWithIdAndTitle product = productRepository.doSomething(3L);
////        System.out.println(product.getId());
////        System.out.println(product.getTitle());
////
////        Product product1 = productRepository.doSomethingSQL();
////
////        Optional<Product> productOptional = productRepository.findById(2L);
////        Product product2 = null;
////        if (productOptional.isPresent()) {
////            product2 = productOptional.get();
////        }
//
////        System.out.println("DEBUG");
//
//        //categoryRepository.deleteById(102L);
//
//        Optional<Category> optionalCategory = categoryRepository.findById(1L);
//
//        Category category = optionalCategory.get();
//
//        System.out.println("Fetched Category");
//
//        List<Product> products = category.getProductList();
//
//        System.out.println("DEBUG");
//    }

}
