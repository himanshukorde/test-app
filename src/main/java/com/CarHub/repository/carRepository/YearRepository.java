package com.CarHub.repository.carRepository;

import com.CarHub.entity.car.Year;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface YearRepository extends JpaRepository<Year, Long> {
    Optional<Year> findByYear(Integer year);
}