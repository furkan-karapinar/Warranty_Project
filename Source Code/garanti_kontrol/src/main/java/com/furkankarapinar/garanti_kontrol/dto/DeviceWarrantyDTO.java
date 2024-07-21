package com.furkankarapinar.garanti_kontrol.dto;

import java.time.LocalDate;

public class DeviceWarrantyDTO {

    private Long deviceId;
    private String serialNumber;
    private String brand;
    private String model;
    private LocalDate purchaseDate;
    private String warrantyStatus;

    // Constractor //
    public DeviceWarrantyDTO(Long deviceId, String serialNumber, String brand, String model, LocalDate purchaseDate, String warrantyStatus) {
        this.deviceId = deviceId;
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.model = model;
        this.purchaseDate = purchaseDate;
        this.warrantyStatus = warrantyStatus;
    }

    // Getters and Setters //

    public Long getDeviceId() {return deviceId;}
    public void setDeviceId(Long deviceId) {this.deviceId = deviceId;}
    public String getSerialNumber() {return serialNumber;}
    public void setSerialNumber(String serialNumber) {this.serialNumber = serialNumber;}
    public String getBrand() {return brand;}
    public void setBrand(String brand) {this.brand = brand;}
    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public String getWarrantyStatus() { return warrantyStatus; }
    public void setWarrantyStatus(String warrantyStatus) { this.warrantyStatus = warrantyStatus; }

    // Getters and Setters //
}
