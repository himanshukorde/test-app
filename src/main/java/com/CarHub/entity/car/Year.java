package com.CarHub.entity.car;

import jakarta.persistence.*;

@Entity
@Table(name = "year")
public class Year {

    public Year(Integer year){
        this.year = year;
    }
    public Year(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "year", nullable = false)
    private Integer year;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}