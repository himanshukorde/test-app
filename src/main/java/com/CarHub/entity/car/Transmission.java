package com.CarHub.entity.car;

import jakarta.persistence.*;

@Entity
@Table(name = "transmission")
public class Transmission {

    public Transmission(String transmission){
        this.transmission = transmission;
    }
    public Transmission(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "transmission", nullable = false)
    private String transmission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}