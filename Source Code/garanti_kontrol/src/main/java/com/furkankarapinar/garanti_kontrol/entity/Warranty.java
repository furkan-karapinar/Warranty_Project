package com.furkankarapinar.garanti_kontrol.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Warranty {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;
    private LocalDate purchaseDate;
    private String warrantyStatus;


    // Getters and Setters //

    public String getWarrantyStatus() {return warrantyStatus;}
    public void setWarrantyStatus(String warrantyStatus) {this.warrantyStatus = warrantyStatus;}
    public LocalDate getPurchaseDate() {return purchaseDate;}
    public void setPurchaseDate(LocalDate purchaseDate) {this.purchaseDate = purchaseDate;}
    public Device getDevice() {return device;}
    public void setDevice(Device device) {this.device = device;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    // Getters and Setters //
}
