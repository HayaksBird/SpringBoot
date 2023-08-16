package com.Test.StudentApp.controller;
import com.Test.StudentApp.entity.Student;
import com.Test.StudentApp.service.IStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller deals with student requests.
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    private final IStudentService service;


    //CONSTRUCTORS
    StudentController(IStudentService service){
        this.service = service;
    }


    /**
     * Show all students.
     */
    @GetMapping("")
    public ResponseEntity<List<Student>> showStudents() {
        return new ResponseEntity<>(service.getStudents(), HttpStatus.OK);
    }


    /**
     * Show student by id.
     */
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> showDesiredStudent(@PathVariable int studentId) {
        return new ResponseEntity<>(service.getStudent(studentId), HttpStatus.OK);
    }


    /**
     * Add a new student
     */
    @PostMapping("")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(service.addStudent(student), HttpStatus.CREATED);
    }


    /**
     * Update an existing student.
     */
    @PutMapping("")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        return new ResponseEntity<>(service.updateStudent(student), HttpStatus.OK);
    }

    /**
     * Delete a student by his id.
     */
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) {
        service.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
