package com.CarHub.repository.carRepository;

import com.CarHub.entity.car.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
   Optional<Transmission> findByTransmissionIgnoreCase(String transmission);

    Optional<Transmission> findByTransmission(String transmission);
}