package com.case_study.ProductApp.exception;

/**
 * This exception is thrown if a product which is to be inserted
 * has an invalid product category id.
 */
public class InvalidProductCategoryException extends  RuntimeException {
    public InvalidProductCategoryException(long id) {
        super(String.format("There exists no product category with id %s!", id));
    }
}
