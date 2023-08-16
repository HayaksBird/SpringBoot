package com.case_study.ProductApp2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles all the exceptions thrown during the run of the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Thrown if an item is not found by the provided value.
     */
    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ItemNotFoundException exc) {
        ProductErrorResponse err = new ProductErrorResponse();

        err.setMessage(exc.getMessage());
        err.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }


    /**
     * Thrown if an item is already exists.
     */
    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ItemAlreadyExistsException exc) {
        ProductErrorResponse err = new ProductErrorResponse();

        err.setMessage(exc.getMessage());
        err.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }


    /**
     * Thrown if any other exception arises.
     *
     * NOTE: These exceptions will be treated as an error on a service side.
     */
    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception exc) {
        ProductErrorResponse err = new ProductErrorResponse();

        err.setMessage(exc.getMessage());
        err.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}