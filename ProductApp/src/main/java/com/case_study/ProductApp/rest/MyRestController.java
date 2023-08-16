package com.case_study.ProductApp.rest;

import com.case_study.ProductApp.entity.Product;
import com.case_study.ProductApp.entity.ProductCategory;
import com.case_study.ProductApp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API controller class.
 * This class manages the communication between the user's request
 * and the service operations.
*/
@RestController
public class MyRestController {
    private final IProductService service;


    @Autowired
    public MyRestController(IProductService service) {
        this.service = service;
    }


    /**
     * Get all products.
     *
     * Returns a status code of 200.
    */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }


    /**
     * Get a product by its id.
     *
     * Returns a status code of 200.
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId) {
        return new ResponseEntity<>(service.getProduct(productId), HttpStatus.OK);
    }


    /**
     * Get a product category description of a product by its id.
     *
     * Returns a status code of 200.
     */
    @GetMapping("/products/{productId}/category")
    public ResponseEntity<ProductCategory> getProductCategoryDescription(@PathVariable long productId) {
        return new ResponseEntity<>(service.getProductCategoryDescription(productId), HttpStatus.OK);
    }


    /**
     * Add new product to the database.
     *
     * NOTE: parameter names must be equivalent to variable names in Product class
     * in order to perform the correct mapping.
     *
     * Returns a status code of 201.
    */
    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.addProduct(product), HttpStatus.CREATED);
    }


    /**
     * Update a product.
     *
     * Returns a status code of 200.
    */
    @PutMapping("/products")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.updateProduct(product), HttpStatus.OK);
    }


    /**
     * Delete a product.
     *
     * Returns a status code of 204.
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId) {
        service.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     * Delete all products.
     *
     * Returns a status code of 204.
     */
    @DeleteMapping("/products")
    public ResponseEntity<Void> deleteAllProducts() {
        service.deleteAllProducts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
