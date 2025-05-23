package com.CarHub.entity.car;

import jakarta.persistence.*;

@Entity
@Table(name = "fuel_type")
public class FuelType {
    public FuelType(String fuelType){
        this.fuelType = fuelType;
    }
    public FuelType(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fuel_type", nullable = false)
    private String fuelType;



    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}