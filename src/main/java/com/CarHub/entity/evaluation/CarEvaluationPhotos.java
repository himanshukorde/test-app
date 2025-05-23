package com.CarHub.entity.evaluation;

import jakarta.persistence.*;

@Entity
@Table(name = "car_evaluation_photos")
public class CarEvaluationPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "url", nullable = false)
    private String url;
    @ManyToOne
    @JoinColumn(name = "car_detailed_evaluation_id")
    private CarDetailedEvaluation carDetailedEvaluation;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CarDetailedEvaluation getCarDetailedEvaluation() {
        return carDetailedEvaluation;
    }

    public void setCarDetailedEvaluation(CarDetailedEvaluation carDetailedEvaluation) {
        this.carDetailedEvaluation = carDetailedEvaluation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}