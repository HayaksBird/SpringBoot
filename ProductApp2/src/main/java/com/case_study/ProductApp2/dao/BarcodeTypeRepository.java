package com.case_study.ProductApp2.dao;

import com.case_study.ProductApp2.entity.BarcodeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeTypeRepository extends JpaRepository<BarcodeType, String> {
}
