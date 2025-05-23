package com.CarHub.payload;

public class CarResponseDTO {
    private Long id;
    private String brand;
    private String model;
    private String fuelType;
    private String transmission;
    private int year;

    public CarResponseDTO(Long id, String brand, String model, String fuelType, String transmission, int year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.year = year;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}

