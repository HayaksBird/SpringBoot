package com.case_study.ProductApp2.service.implementation;

import com.case_study.ProductApp2.dao.UnitRepository;
import com.case_study.ProductApp2.entity.Product;
import com.case_study.ProductApp2.entity.Unit;
import com.case_study.ProductApp2.exception.ItemAlreadyExistsException;
import com.case_study.ProductApp2.exception.ItemNotFoundException;
import com.case_study.ProductApp2.service.IUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An implementation class for the IUnitService.
 */
@Service
public class UnitService implements IUnitService {
    private final UnitRepository unitRepository;


    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }


    @Override
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }


    @Override
    public Unit getUnit(String unit) {
        var record = unitRepository.findById(unit);

        if (record.isPresent()) {
            return record.get();
        } else throw new ItemNotFoundException(unit);
    }


    @Override
    public List<Product> getProducts(String unit) {
        var record = unitRepository.findById(unit);

        if (record.isPresent()) {
            return record.get().getProducts();
        } else throw new ItemNotFoundException(unit);
    }


    @Override
    public void addUnit(Unit unit) {
        if (unitRepository.findById(unit.getUnit()).isEmpty()) {
            unitRepository.save(unit);
        } else throw new ItemAlreadyExistsException(unit.getUnit());
    }


    @Override
    public void deleteUnit(String unit) {
        var record = unitRepository.findById(unit);

        if (record.isPresent()) {
            unitRepository.delete(record.get());
        } else throw new ItemNotFoundException(unit);
    }


    @Override
    public void deleteAllUnits() {
        unitRepository.deleteAll();
    }
}
