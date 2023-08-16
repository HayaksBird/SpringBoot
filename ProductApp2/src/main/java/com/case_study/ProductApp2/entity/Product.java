package com.case_study.ProductApp2.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * The product field.
 */
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "code")
    private int code;

    @Column(name = "category_id")
    private int productCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "unit")
    private String unit;

    /**
     *  For @OneToMany:
     *      if the user decides to delete the product entity, then all the
     *      corresponding barcodes should be deleted as well.
     *  For @JoinColumn:
     *      it is not this program's responsibility to know how to generate
     *      barcodes for the product. Thus, it is left for the server to manage
     *      the barcodes.
     */
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "product_code",
            referencedColumnName = "code",
            insertable = false,
            updatable = false)
    private List<Barcode> barcodes;


    //CONSTRUCTORS
    public Product() {}


    /**
     * For product creation/update
     */
    @JsonCreator
    public Product(@JsonProperty("code") int code,
                   @JsonProperty("category_id") int productCategory,
                   @JsonProperty("name") String name,
                   @JsonProperty("brand") String brand,
                   @JsonProperty("unit") String unit) {

        this.code = code;
        this.productCategory = productCategory;
        this.name = name;
        this.brand = brand;
        this.unit = unit;
    }


    //Setters & Getters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Barcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<Barcode> barcodes) {
        this.barcodes = barcodes;
    }
}