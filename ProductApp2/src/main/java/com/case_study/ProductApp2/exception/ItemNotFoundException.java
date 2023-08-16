package com.case_study.ProductApp2.exception;

/**
 * This exception is thrown if an item has not been found.
 */
public class ItemNotFoundException extends RuntimeException {
    public <T> ItemNotFoundException(T value) {
        super(String.format("An item with the key value of '%s' was not found!", value));
    }
}
