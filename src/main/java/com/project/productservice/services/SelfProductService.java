package com.project.productservice.services;

import com.project.productservice.dtos.FakeStoreProductDTO;
import com.project.productservice.dtos.ProductDTO;
import com.project.productservice.exceptions.CategoryNotFoundException;
import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.model.Category;
import com.project.productservice.model.Product;
import com.project.productservice.repository.CategoryRepository;
import com.project.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ProductDTO getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()) {
            return convertProductToDTO(product.get());
        } else {
            throw new ProductNotFoundException(id, "Invalid product id passed, Please retry with a valid product id");
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList =  productRepository.findAll();
        List<ProductDTO> productDTOList = null;
        productDTOList = new ArrayList<>();
        for(Product product : productList) {
            productDTOList.add(convertProductToDTO(product));
        }
        return productDTOList;
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException {
        Category category = product.getCategory();
        Category getCategoryByName = categoryRepository.findByCategoryNameIgnoreCase(category.getCategoryName());
        if(category.getId() ==null && category.getCategoryName() == null){
            throw new CategoryNotFoundException(null, "Invalid category details passed, Please retry with a valid category details");
        }

        if (category.getId() == null) {
            if(getCategoryByName != null) {
                product.setCategory(getCategoryByName);
            }
        } else {
            Optional<Category> categoryOptional = categoryRepository.findById(category.getId());
            categoryOptional.ifPresent(product::setCategory);
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
    public Product updateProduct(Long id, Product dataToUpdate) throws CategoryNotFoundException, ProductNotFoundException {

        Optional<Product> getExistingProduct = productRepository.findById(id);
        if (getExistingProduct.isPresent()) {
            Product existingProduct = getExistingProduct.get();
            if (dataToUpdate.getDescription() != null) {
                existingProduct.setDescription(dataToUpdate.getDescription());
            }
            if (dataToUpdate.getImage() != null) {
                existingProduct.setImage(dataToUpdate.getImage());
            }
            if (dataToUpdate.getPrice() != 0) {
                existingProduct.setPrice(dataToUpdate.getPrice());
            }
            if (dataToUpdate.getTitle() != null) {
                existingProduct.setTitle(dataToUpdate.getTitle());
            }
            if(dataToUpdate.getCategory() != null) {
                Category category = dataToUpdate.getCategory();
                Optional<Category> getCategory = categoryRepository.findById(category.getId());
                if(getCategory.isPresent()) {
                    existingProduct.setCategory(getCategory.get());
                } else {
                    throw new CategoryNotFoundException(category.getId(), "Invalid category details are passed, Please retry with a valid category details.");
                }

            }
            return productRepository.save(existingProduct);
        } else {
            throw new ProductNotFoundException(id, "Invalid product id passed, Please retry with a valid product id");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    public ProductDTO convertProductToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());

        Category category = new Category();
        category.setCategoryName(product.getCategory().getCategoryName());
        productDTO.setCategory(category.getCategoryName());
        return productDTO;
    }
}
