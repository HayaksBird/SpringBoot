package com.case_study.ProductApp2.rest;

import com.case_study.ProductApp2.entity.Barcode;
import com.case_study.ProductApp2.entity.BarcodeType;
import com.case_study.ProductApp2.service.IBarcodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles all the requests sent to the barcode_types
 * endpoints.
 */
@RestController
@RequestMapping("/barcode_types")
public class BarcodeController {
    private final IBarcodeService service;


    public BarcodeController(IBarcodeService service) {
        this.service = service;
    }


    @GetMapping("")
    public ResponseEntity<List<BarcodeType>> getAllBarcodes() {
        return new ResponseEntity<>(service.getAllBarcodeTypes(), HttpStatus.OK);
    }


    @GetMapping("/{type}")
    public ResponseEntity<BarcodeType> getBarcodeTypes(@PathVariable String type) {
        return new ResponseEntity<>(service.getBarcodeType(type), HttpStatus.OK);
    }


    @GetMapping("/{type}/barcodes")
    public ResponseEntity<List<Barcode>> getBarcodes(@PathVariable String type) {
        return new ResponseEntity<>(service.getBarcodes(type), HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Void> addBarcodeType(@RequestBody BarcodeType barcodeType) {
        service.addBarcodeType(barcodeType);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
