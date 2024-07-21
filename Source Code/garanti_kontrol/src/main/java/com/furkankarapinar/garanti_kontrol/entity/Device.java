package com.furkankarapinar.garanti_kontrol.entity;
import jakarta.persistence.*;
import java.util.List;


@Entity
public class Device {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNumber;
    private String brand;
    private String model;
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<Warranty> warranties;


    // Getters and Setters //

    public String getSerialNumber() {return serialNumber;}
    public void setSerialNumber(String serialNumber) {this.serialNumber = serialNumber;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getBrand() {return brand;}
    public void setBrand(String brand) {this.brand = brand;}
    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}

    // Getters and Setters //
}