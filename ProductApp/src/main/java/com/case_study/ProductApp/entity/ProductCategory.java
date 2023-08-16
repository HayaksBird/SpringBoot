package com.case_study.ProductApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class for the product category table
 */
@Entity
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "description")
    private String description;


    //CONSTRUCTORS
    public ProductCategory() {}


    public ProductCategory(long id, String description) {
        this.id = id;
        this.description = description;
    }


    //Setters & Getters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
