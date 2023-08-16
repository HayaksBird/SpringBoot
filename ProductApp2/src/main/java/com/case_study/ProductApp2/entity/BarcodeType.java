package com.case_study.ProductApp2.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * The Barcode type field.
 */
@Entity
@Table(name = "barcode_type")
public class BarcodeType {
    @Id
    @Column(name = "type")
    private String type;

    @OneToMany
    @JsonIgnore
    @JoinColumn(name = "type", referencedColumnName = "type")
    private List<Barcode> barcodes;


    //CONSTRUCTORS
    public BarcodeType() {}


    @JsonCreator
    public BarcodeType(@JsonProperty("type") String type) {
        this.type = type;
    }


    //Setters & Getters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Barcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<Barcode> barcodes) {
        this.barcodes = barcodes;
    }
}
