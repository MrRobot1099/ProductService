package com.project.productservice.services;

import com.project.productservice.exceptions.CategoryNotFoundException;
import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.model.Category;
import com.project.productservice.model.Product;
import com.project.productservice.repository.CategoryRepository;
import com.project.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException {
        Category category = product.getCategory();
        Category getCategory = categoryRepository.findByCategoryNameIgnoreCase(category.getCategoryName());

        if (category.getId() == null) {
            if(getCategory == null) {
                //first save the category in the DB
                Category savedCategory = categoryRepository.save(category);
                product.setCategory(savedCategory);
            } else {
                product.setCategory(getCategory);
            }
        } else {
            Optional<Category> categoryOptional = categoryRepository.findById(category.getId());
            if(categoryOptional.isPresent()) {
                product.setCategory(categoryOptional.get());
            } else {
                throw new CategoryNotFoundException(category.getId(), "Invalid category details are passed, Please retry with a valid category details.");
            }
        }

        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException, CategoryNotFoundException {
        Optional<Product> getProduct = productRepository.findById(id);
        Optional<Category> getCurrentCategoryById = categoryRepository.findById(product.getCategory().getId());
        if(getProduct.isPresent()) {
            Product productToReplace = getProduct.get();
            productToReplace.setDescription(product.getDescription());
            productToReplace.setImage(product.getImage());
            productToReplace.setPrice(product.getPrice());
            productToReplace.setTitle(product.getTitle());
            if (getCurrentCategoryById.isPresent()) {
                productToReplace.setCategory(getCurrentCategoryById.get());
            } else {
                throw new CategoryNotFoundException(product.getCategory().getId(), "Invalid category details are passed, Please retry with a valid category details.");
            }
            return productRepository.save(productToReplace);
        } else {
            throw new ProductNotFoundException(id, "Invalid product id passed, Please retry with a valid product id");
        }
    }

    @Override
    public Product updateProduct(Long id, Map<String, Object> dateToUpdate) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
