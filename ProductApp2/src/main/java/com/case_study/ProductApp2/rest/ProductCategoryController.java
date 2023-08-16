package com.case_study.ProductApp2.rest;

import com.case_study.ProductApp2.entity.Product;
import com.case_study.ProductApp2.entity.ProductCategory;
import com.case_study.ProductApp2.service.IProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles all the requests sent to the product_categories
 * endpoints.
 */
@RestController
@RequestMapping("/product_categories")
public class ProductCategoryController {
    private final IProductCategoryService service;


    public ProductCategoryController(IProductCategoryService service) {
        this.service = service;
    }


    @GetMapping("")
    public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
        return new ResponseEntity<>(service.getAllProductCategories(), HttpStatus.OK);
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<ProductCategory> getProductCategory(@PathVariable int categoryId) {
        return new ResponseEntity<>(service.getProductCategory(categoryId), HttpStatus.OK);
    }


    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<Product>> getProducts(@PathVariable int categoryId) {
        return new ResponseEntity<>(service.getProducts(categoryId), HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Void> addProductCategory(@RequestBody ProductCategory productCategory) {
        service.addProductCategory(productCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ProductCategory> deleteProductCategory(@PathVariable int categoryId) {
        service.deleteProductCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("")
    public ResponseEntity<ProductCategory> deleteAllProductCategory() {
        service.deleteAllProductCategories();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
