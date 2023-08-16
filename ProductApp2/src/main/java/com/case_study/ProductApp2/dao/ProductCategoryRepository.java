package com.case_study.ProductApp2.dao;

import com.case_study.ProductApp2.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    /**
     * JPA will automatically implement this method by parsing the method's name.
     */
    public Optional<ProductCategory> findByDescription(String description);
}
