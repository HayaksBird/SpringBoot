package com.case_study.ProductApp.service;

import com.case_study.ProductApp.dao.ProductCategoryRepository;
import com.case_study.ProductApp.dao.ProductRepository;
import com.case_study.ProductApp.entity.Product;
import com.case_study.ProductApp.entity.ProductCategory;
import com.case_study.ProductApp.exception.InvalidProductCategoryException;
import com.case_study.ProductApp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation class for the IProductService interface.
*/
@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepos;
    private final ProductCategoryRepository productCategoryRepos;


    @Autowired
    public ProductService(ProductRepository productRepos, ProductCategoryRepository productCategoryRepos) {
        this.productRepos = productRepos;
        this.productCategoryRepos = productCategoryRepos;
    }


    /**
     * Get all products
    */
    @Override
    public List<Product> getAllProducts() {
        return productRepos.findAll();
    }


    /**
     * Get a product by its id.
     *
     * Note: Throws an exception if the product is not found
     */
    @Override
    public Product getProduct(long id) {
         Optional<Product> product = productRepos.findById(id);

         if (product.isEmpty()) throw new ProductNotFoundException(id);

         return product.get();
    }


    /**
     * Get a product category description of a product by its id.
     *
     * Note: Throws an exception if the product is not found
     */
    @Override
    public ProductCategory getProductCategoryDescription(long id) {
        Optional<Product> product = productRepos.findById(id);

        if (product.isPresent()) return product.get().getProductCategory();

        throw new ProductNotFoundException(id);
    }


    /**
     * Insert a product.
     */
    @Override
    public String addProduct(Product product) {
        //Check the product category by its id to verify its existence
        if (productCategoryRepos.findById(product.getProductCategoryId()).isPresent()) {
            productRepos.save(product);
            return String.format("Product %s has been successfully added!", product.getDescription());
        }

        throw new InvalidProductCategoryException(product.getProductCategoryId());
    }


    /**
     * Update a product.
     *
     * Note: Throws an exception if the product is not found or
     * if the product category does not exist in the system.
     */
    @Override
    public String updateProduct(Product product) {
        //Check the product by its id to verify its existence
        if (productRepos.findById(product.getId()).isPresent()) {
            //Check the product category by its id to verify its existence
            if (productCategoryRepos.findById(product.getProductCategoryId()).isPresent()) {
                productRepos.save(product);

                return String.format("Product with id %s has been successfully updated!", product.getId());
            }

            throw new InvalidProductCategoryException(product.getProductCategoryId());
        }

        throw new ProductNotFoundException(product.getId());
    }


    /**
     * Delete a product by its id.
     *
     * Note: Throws an exception if the product is not found
     */
    @Override
    public void deleteProduct(long id) {
        Optional<Product> product = productRepos.findById(id);

        if (product.isPresent()) {
            productRepos.delete(product.get());
            return;
        }

        throw new ProductNotFoundException(id);
    }


    /**
     * Delete all products.
     */
    @Override
    public void deleteAllProducts() {
        productRepos.deleteAll();
    }
}