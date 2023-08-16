package com.case_study.ProductApp.exception;

/**
 * This exception is thrown if a product has not been found.
*/
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(long id) {
        super(String.format("A product with id %s was not found!", id));
    }
}
