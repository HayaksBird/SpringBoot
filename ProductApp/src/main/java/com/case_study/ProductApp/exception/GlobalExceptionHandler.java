package com.case_study.ProductApp.exception;

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
     * Thrown if a product is not found by its id.
    */
    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException exc) {
        ProductErrorResponse err = new ProductErrorResponse();

        err.setMessage(exc.getMessage());
        err.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }


    /**
     * Thrown if a product which is to be inserted has an invalid product category id.
     */
    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(InvalidProductCategoryException exc) {
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
