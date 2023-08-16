package com.Test.StudentApp.dao;

import com.Test.StudentApp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO for the student
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
