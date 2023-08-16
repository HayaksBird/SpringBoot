package com.case_study.ProductApp2.service;

import com.case_study.ProductApp2.entity.Product;
import com.case_study.ProductApp2.entity.Unit;

import java.util.List;

/**
 * This service allows to perform operations on the unit table.
 */
public interface IUnitService {
    /**
     * Retrieve all the existing units.
     */
    List<Unit> getAllUnits();


    /**
     * Retrieve a specific unit.
     */
    Unit getUnit(String unit);


    /**
     * Retrieve all products of a specific unit.
     */
    List<Product> getProducts(String unit);


    /**
     * Add a new unit.
     */
    void addUnit(Unit unit);


    /**
     * Delete a specific unit.
     */
    void deleteUnit(String unit);


    /**
     * Delete all units.
     */
    void deleteAllUnits();
}
