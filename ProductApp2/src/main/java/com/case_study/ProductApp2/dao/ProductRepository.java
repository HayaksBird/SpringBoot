package com.case_study.ProductApp2.dao;

import com.case_study.ProductApp2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
