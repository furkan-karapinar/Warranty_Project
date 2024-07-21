package com.furkankarapinar.garanti_kontrol.dto;

import java.time.LocalDate;

public class DeviceDTO {

    private Long id;
    private String brand;
    private String model;
    private String serialNumber;
    private LocalDate purchaseDate;
    private String warrantyStatus;


    // Getters and Setters //

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public LocalDate getPurchaseDate() { return purchaseDate;}
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public String getWarrantyStatus() { return warrantyStatus; }
    public void setWarrantyStatus(String warrantyStatus) { this.warrantyStatus = warrantyStatus; }

    // Getters and Setters //
}
