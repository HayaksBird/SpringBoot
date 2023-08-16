package com.case_study.ProductApp.dao;

import com.case_study.ProductApp.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface will be used through its implementation, and it is a DAO for
 * the ProductCategory class. However, we will not handle the implementation at all,
 * since we will use it via dependency injection.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
