package com.case_study.ProductApp2.service.implementation;

import com.case_study.ProductApp2.dao.ProductRepository;
import com.case_study.ProductApp2.entity.Product;
import com.case_study.ProductApp2.exception.ItemNotFoundException;
import com.case_study.ProductApp2.service.IProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An implementation class for the IProductService.
 */
@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public Product getProduct(int code) {
        var record = productRepository.findById(code);

        if (record.isPresent()) return record.get();
        else throw new ItemNotFoundException(code);
    }


    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }


    @Override
    public void deleteProduct(int code) {
        var record = productRepository.findById(code);

        if (record.isPresent()) productRepository.delete(record.get());
        else throw new ItemNotFoundException(code);
    }


    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }


    @Override
    @Transactional
    public void updateProduct(Product product) {
        if (productRepository.findById(product.getCode()).isPresent()) {
            productRepository.save(product);
        } else throw new ItemNotFoundException(product.getCode());
    }
}
