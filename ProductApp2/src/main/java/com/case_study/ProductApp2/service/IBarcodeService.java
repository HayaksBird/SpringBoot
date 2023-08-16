package com.case_study.ProductApp2.service;

import com.case_study.ProductApp2.entity.Barcode;
import com.case_study.ProductApp2.entity.BarcodeType;

import java.util.List;

/**
 * This service allows to perform operations on the barcode_fields table.
 */
public interface IBarcodeService {
    /**
     * Retrieve all the existing barcode types.
     */
    List<BarcodeType> getAllBarcodeTypes();


    /**
     * Retrieve a specific barcode type.
     */
    BarcodeType getBarcodeType(String type);


    /**
     * Retrieve all products of a specific barcode type.
     */
    List<Barcode> getBarcodes(String type);


    /**
     * Add a new barcode type.
     */
    void addBarcodeType(BarcodeType barcodeType);
}
