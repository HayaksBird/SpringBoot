package com.case_study.ProductApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Entity class for the product table
 */
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "product_category_id")
    private long productCategoryId;

    /*
     * @ManyToOne annotation is used, because multiple products could be linked to a certain
     * product category.
     * Through @JoinColumn annotation we specify that the product_category_id
     * column in the product table is the foreign key, and that it is linked to the id column of the
     * product_category table.
     *
     * insertable & updatable are set to false, because I will manage the column with foreign
     * key manually.
     *
     * When retrieving a product from the product table, this field will be automatically filled
     * with the corresponding product category. Basically, JPA will perform the join operation on the
     * background.
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductCategory productCategory;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;


    //CONSTRUCTORS
    public Product() {}


    public Product(long productCategoryId, String description, BigDecimal price) {
        this.productCategoryId = productCategoryId;
        this.description = description;
        this.price = price;
    }


    //Setters & Getters
    //NOTE: no setter for the productCategory, because it is predetermined
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}