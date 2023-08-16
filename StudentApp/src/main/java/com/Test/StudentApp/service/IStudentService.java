package com.Test.StudentApp.service;

import com.Test.StudentApp.entity.Student;
import com.Test.StudentApp.entity.User;

import java.util.List;

public interface IStudentService {
    /**
     * Get a list of all students.
    */
    List<Student> getStudents();


    /**
     * Get a student by their id.
     * If the student id is not registered in the database, then an exception is thrown!
     */
    Student getStudent(int id);


    /**
     * Add a student to the database.
    */
    String addStudent(Student student);


    /**
     * Update a student in the database.
     * If the student id is not registered in the database, then an exception is thrown!
    */
    String updateStudent(Student student);


    /**
     * Delete a student from a database.
     * If the student id is not registered in the database, then an exception is thrown!
    */
    void deleteStudent(int id);
}
