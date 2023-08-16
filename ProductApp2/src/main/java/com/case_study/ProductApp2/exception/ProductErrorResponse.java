package com.case_study.ProductApp2.exception;

/**
 * This class is the message body of the HTTP response object, when an error has occurred.
 */
public class ProductErrorResponse {
    String message;
    int statusCode;


    //CONSTRUCTORS
    public ProductErrorResponse() {}


    public ProductErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }


    //Setters & Getters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}