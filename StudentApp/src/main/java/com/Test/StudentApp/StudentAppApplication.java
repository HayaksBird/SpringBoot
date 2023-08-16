package com.Test.StudentApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This application allows a user to interact with the student's database.
 * It allows to perform CRUD operations on it. In addition to that, this application
 * implements a role-based security system with the help of JWT.
 *
 * NOTE: For more info check the READ_ME file appended to this project.
 */
@SpringBootApplication
public class StudentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentAppApplication.class, args);
	}

}
