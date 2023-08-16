package com.Test.StudentApp.exception;

/**
 * This exception is thrown if a student is not found in the database.
 */
public class StudentNotFoundException extends RuntimeException {
    //CONSTRUCTORS
    public StudentNotFoundException(int id) {
        super(String.format("Student with id %s does not exist!", id));
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}
