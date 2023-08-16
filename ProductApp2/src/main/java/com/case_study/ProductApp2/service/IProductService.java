package com.case_study.ProductApp2.service;

import com.case_study.ProductApp2.entity.Product;

import java.util.List;

/**
 * This service allows to perform operations on the product table.
 */
public interface IProductService {
    /**
     * Retrieve all the existing products.
     */
    List<Product> getAllProducts();


    /**
     * Retrieve a specific product.
     */
    Product getProduct(int code);


    /**
     * Add a new product.
     */
    void addProduct(Product product);


    /**
     * Delete a specific product.
     */
    void deleteProduct(int code);


    /**
     * Delete all products.
     */
    void deleteAllProducts();


    /**
     * Update a specific product.
     */
    void updateProduct(Product product);
}
