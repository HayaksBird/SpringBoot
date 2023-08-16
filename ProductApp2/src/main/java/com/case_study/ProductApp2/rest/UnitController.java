package com.case_study.ProductApp2.rest;

import com.case_study.ProductApp2.entity.Product;
import com.case_study.ProductApp2.entity.Unit;
import com.case_study.ProductApp2.service.IUnitService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles all the requests sent to the units
 * endpoints.
 */
@RestController
@RequestMapping("/units")
public class UnitController {
    private final IUnitService service;


    public UnitController(IUnitService service) {
        this.service = service;
    }


    @GetMapping("")
    public ResponseEntity<List<Unit>> getAllUnits() {
        return new ResponseEntity<>(service.getAllUnits(), HttpStatus.OK);
    }


    @GetMapping("/{unit}")
    public ResponseEntity<Unit> getUnit(@PathVariable String unit) {
        return new ResponseEntity<>(service.getUnit(unit), HttpStatus.OK);
    }


    @GetMapping("/{unit}/products")
    public ResponseEntity<List<Product>> getProducts(@PathVariable String unit) {
        return new ResponseEntity<>(service.getProducts(unit), HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Void> addUnit(@RequestBody Unit unit) {
        service.addUnit(unit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/{unit}")
    public ResponseEntity<Void> deleteUnit(@PathVariable String unit) {
        service.deleteUnit(unit);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("")
    public ResponseEntity<Void> deleteAllUnits() {
        service.deleteAllUnits();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}