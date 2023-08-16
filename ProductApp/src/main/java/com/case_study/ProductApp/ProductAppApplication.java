package com.case_study.ProductApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This application utilizes the REST API in order to provide a full
 * CRUD support for the product table.
 *
 * NOTE:
 * Although a user can view the product category description of a product,
 * they cannot add/delete a new product category, just as they cannot update an
 * existing product category description.
*/
@SpringBootApplication
public class ProductAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductAppApplication.class, args);
	}

}
