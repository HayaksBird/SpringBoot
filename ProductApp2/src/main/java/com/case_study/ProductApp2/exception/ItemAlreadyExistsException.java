package com.case_study.ProductApp2.exception;

/**
 * This exception is thrown if an item already exists.
 */
public class ItemAlreadyExistsException extends RuntimeException {
    public <T> ItemAlreadyExistsException(T value) {
        super(String.format("An item with the key value of '%s' already exists!", value));
    }
}
