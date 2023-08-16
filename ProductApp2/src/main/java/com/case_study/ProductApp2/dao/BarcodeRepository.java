package com.case_study.ProductApp2.dao;

import com.case_study.ProductApp2.entity.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<Barcode, Integer> {

}
