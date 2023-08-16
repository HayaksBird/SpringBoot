package com.case_study.ProductApp2.rest;

import com.case_study.ProductApp2.entity.Product;
import com.case_study.ProductApp2.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles all the requests sent to the products
 * endpoints.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService service;


    public ProductController(IProductService service) {
        this.service = service;
    }


    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }


    @GetMapping("/{productCode}")
    public ResponseEntity<Product> getProduct(@PathVariable int productCode) {
        return new ResponseEntity<>(service.getProduct(productCode), HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        service.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/{productCode}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productCode) {
        service.deleteProduct(productCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("")
    public ResponseEntity<Void> deleteAllProducts() {
        service.deleteAllProducts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        service.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
