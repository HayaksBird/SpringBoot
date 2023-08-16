package com.case_study.ProductApp2.service;

import com.case_study.ProductApp2.entity.Product;
import com.case_study.ProductApp2.entity.ProductCategory;

import java.util.List;

/**
 * This service allows to perform operations on the product_category table.
 */
public interface IProductCategoryService {
    /**
     * Retrieve all the existing product categories.
     */
    List<ProductCategory> getAllProductCategories();


    /**
     * Retrieve a specific product category.
     */
    ProductCategory getProductCategory(int categoryId);


    /**
     * Retrieve all products of a specific product category.
     */
    List<Product> getProducts(int categoryId);


    /**
     * Add a new product category.
     */
    void addProductCategory(ProductCategory productCategory);


    /**
     * Delete a specific product category.
     */
    void deleteProductCategory(int categoryId);


    /**
     * Delete all product categories
     */
    void deleteAllProductCategories();
}
