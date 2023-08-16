package com.case_study.ProductApp2.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

/**
 * The unit field.
 */
@Entity
@Table(name = "unit")
public class Unit {
    @Id
    @Column(name = "unit")
    private String unit;

    /**
     *  For @OneToMany:
     *      If a user decides to delete a unit, then all
     *      the corresponding products should be deleted.
     */
    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "unit",
                referencedColumnName = "unit")
    private List<Product> products;


    //CONSTRUCTORS
    public Unit() {}


    @JsonCreator
    public Unit(@JsonProperty("unit") String unit) {
        this.unit = unit;
    }


    //Setters & Getters
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
