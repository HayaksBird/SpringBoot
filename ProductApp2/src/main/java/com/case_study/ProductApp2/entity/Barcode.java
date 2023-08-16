package com.case_study.ProductApp2.entity;

import jakarta.persistence.*;

/**
 * The Barcode field of the product.
 */
@Entity
@Table(name = "barcode")
public class Barcode {
    @Id
    @Column(name = "code")
    private int code;

    @Column(name = "type")
    private String type;


    //CONSTRUCTORS
    public Barcode() {}


    //Setters & Getters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
