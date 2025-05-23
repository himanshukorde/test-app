package com.CarHub.entity.evaluation;

import jakarta.persistence.*;

@Entity
@Table(name = "car_detailed_evaluation")
public class CarDetailedEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "kms", nullable = false)
    private String kms;

    @Column(name = "year_of_manufacturing", nullable = false)
    private int yearOfManufacturing;

    public int getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(int yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}