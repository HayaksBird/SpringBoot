package com.case_study.ProductApp2.service.implementation;

import com.case_study.ProductApp2.dao.ProductCategoryRepository;
import com.case_study.ProductApp2.entity.Product;
import com.case_study.ProductApp2.entity.ProductCategory;
import com.case_study.ProductApp2.exception.ItemAlreadyExistsException;
import com.case_study.ProductApp2.exception.ItemNotFoundException;
import com.case_study.ProductApp2.service.IProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An implementation class for the IProductCategoryService.
 */
@Service
public class ProductCategoryService implements IProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;


    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }


    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }


    @Override
    public ProductCategory getProductCategory(int categoryId) {
        var record = productCategoryRepository.findById(categoryId);

        if (record.isPresent()) return record.get();
        else throw new ItemNotFoundException(categoryId);
    }


    @Override
    public List<Product> getProducts(int categoryId) {
        var record = productCategoryRepository.findById(categoryId);

        if (record.isPresent()) return record.get().getProducts();
        else throw new ItemNotFoundException(categoryId);
    }


    @Override
    public void addProductCategory(ProductCategory productCategory) {
        if (productCategoryRepository.findByDescription(productCategory.getDescription()).isEmpty()) {
            productCategoryRepository.save(productCategory);
        } else throw new ItemAlreadyExistsException(productCategory.getDescription());


    }


    @Override
    public void deleteProductCategory(int categoryId) {
        var record = productCategoryRepository.findById(categoryId);

        if (record.isPresent()) productCategoryRepository.delete(record.get());
        else throw new ItemNotFoundException(categoryId);
    }


    @Override
    public void deleteAllProductCategories() {
        productCategoryRepository.deleteAll();
    }
}
