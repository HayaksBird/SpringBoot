package com.case_study.ProductApp.service;

import com.case_study.ProductApp.entity.Product;
import com.case_study.ProductApp.entity.ProductCategory;

import java.util.List;

/**
 * An interface for the product service class to be implemented.
 * Contains all the operations that may be requested from
 * the user.
*/
public interface IProductService {
    /**
     * Get all products.
    */
    List<Product> getAllProducts();


    /**
     * Get a product by its id.
     */
    Product getProduct(long id);


    /**
     * Get a product category description of a product by its id.
     */
    ProductCategory getProductCategoryDescription(long id);


    /**
     * Insert a product.
    */
    String addProduct(Product product);


    /**
     * Update a product.
    */
    String updateProduct(Product product);


    /**
     * Delete a product by its id.
    */
    void deleteProduct(long id);


    /**
     * Delete all products.
    */
    void deleteAllProducts();
}
