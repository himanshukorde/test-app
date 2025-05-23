package com.CarHub.repository.carRepository;

import com.CarHub.entity.car.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    Optional<FuelType> findByFuelTypeIgnoreCase(String fuelType);

    Optional<FuelType> findByFuelType(String fuelType);
}