package com.case_study.ProductApp.dao;

import com.case_study.ProductApp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface will be used through its implementation, and it is a DAO for
 * the Product class. However, we will not handle the implementation at all,
 * since we will use it via dependency injection.
*/
public interface ProductRepository extends JpaRepository<Product, Long> {

}
