package com.case_study.ProductApp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This program creates a REST API for the user to interact
 * with the assortment database. A user is given a wide range CRUD
 * operations for his needs.
 *
 * NOTE: For more detailed information check the READ_ME file appended
 * in this project.
 */
@SpringBootApplication
public class ProductApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(ProductApp2Application.class, args);
	}

}
