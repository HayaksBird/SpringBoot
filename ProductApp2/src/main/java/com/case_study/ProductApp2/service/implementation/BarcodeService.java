package com.case_study.ProductApp2.service.implementation;

import com.case_study.ProductApp2.dao.BarcodeTypeRepository;
import com.case_study.ProductApp2.entity.Barcode;
import com.case_study.ProductApp2.entity.BarcodeType;
import com.case_study.ProductApp2.exception.ItemAlreadyExistsException;
import com.case_study.ProductApp2.exception.ItemNotFoundException;
import com.case_study.ProductApp2.service.IBarcodeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An implementation class for the IBarcodeService.
 */
@Service
public class BarcodeService implements IBarcodeService {
    private final BarcodeTypeRepository barcodeTypeRepository;


    public BarcodeService(BarcodeTypeRepository barcodeTypeRepository) {
        this.barcodeTypeRepository = barcodeTypeRepository;
    }


    @Override
    public List<BarcodeType> getAllBarcodeTypes() {
        return barcodeTypeRepository.findAll();
    }


    @Override
    public BarcodeType getBarcodeType(String type) {
        var record = barcodeTypeRepository.findById(type);

        if (record.isPresent()) {
            return record.get();
        } else throw new ItemNotFoundException(type);
    }


    @Override
    public List<Barcode> getBarcodes(String type) {
        var record = barcodeTypeRepository.findById(type);

        if (record.isPresent()) {
            return record.get().getBarcodes();
        } else throw new ItemNotFoundException(type);
    }


    @Override
    public void addBarcodeType(BarcodeType barcodeType) {
        if (barcodeTypeRepository.findById(barcodeType.getType()).isEmpty()) {
            barcodeTypeRepository.save(barcodeType);
        } else throw new ItemAlreadyExistsException(barcodeType.getType());
    }
}