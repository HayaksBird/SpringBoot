package com.Test.StudentApp.service;

import com.Test.StudentApp.dao.StudentRepository;
import com.Test.StudentApp.dao.UserRepository;
import com.Test.StudentApp.entity.Student;
import com.Test.StudentApp.entity.User;
import com.Test.StudentApp.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The implementation class of the student service.
 */
@Service
public class StudentService implements IStudentService {
    private final StudentRepository studentRepos;


    //CONSTRUCTORS
    public StudentService(StudentRepository studentRepos) {
        this.studentRepos = studentRepos;
    }


    /**
     * Get a list of all students.
     */
    @Override
    public List<Student> getStudents() {
        return studentRepos.findAll();
    }


    /**
     * Get a student by their id.
     * If the student id is not registered in the database, then an exception is thrown!
     */
    @Override
    public Student getStudent(int id) {
        Optional<Student> student = studentRepos.findById(id);

        if (student.isEmpty()) throw new StudentNotFoundException(id);

        return student.get();
    }


    /**
     * Add a student to the database.
     */
    @Override
    @Transactional
    public String addStudent(Student student) {
        studentRepos.save(student);
        return "Student has been successfully added!";
    }


    /**
     * Update a student in the database.
     * If the student id is not registered in the database, then an exception is thrown!
     */
    @Override
    @Transactional
    public String updateStudent(Student student) {
        //Check the student by their id to verify their existence
        if (studentRepos.findById(student.getId()).isPresent()) {
            studentRepos.save(student);

            return String.format("Product with id %s has been successfully updated!", student.getId());
        }

        throw new StudentNotFoundException(student.getId());
    }


    /**
     * Delete a student from a database.
     * If the student id is not registered in the database, then an exception is thrown!
     */
    @Override
    @Transactional
    public void deleteStudent(int id) {
        Optional<Student> student = studentRepos.findById(id);

        if (student.isPresent()) {
            studentRepos.delete(student.get());
            return;
        }

        throw new StudentNotFoundException(id);
    }
}
