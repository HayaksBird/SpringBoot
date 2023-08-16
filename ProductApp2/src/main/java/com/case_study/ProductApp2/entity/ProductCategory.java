package com.case_study.ProductApp2.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * The product category field.
 */
@Entity
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;


    /**
     *  For @OneToMany:
     *      If a user decides to delete a product category, then all
     *      the corresponding products should be deleted.
     */
    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "category_id",
                referencedColumnName = "id")
    private List<Product> products;


    //CONSTRUCTORS
    public ProductCategory() {

    }


    @JsonCreator
    public ProductCategory(@JsonProperty("description") String description) {
        this.description = description;
    }


    //Setters & Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
