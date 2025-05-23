package com.CarHub.repository.carRepository;

import com.CarHub.entity.car.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {
    Optional<Model>findByNameIgnoreCase(String model);

    Optional<Model> findByName(String model);
}