package com.Test.StudentApp.exception;

/**
 * A response class that will encapsulate the error message of
 * any relative to the Student class exception thrown.
*/
public class StudentErrorResponse {
    String message;

    int status;

    long timeStamp;


    //CONSTRUCTORS
    public StudentErrorResponse() {}


    public StudentErrorResponse(String message, int status, long timeStamp) {
        this.message = message;
        this.status = status;
        this.timeStamp = timeStamp;
    }


    //Getters & Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
