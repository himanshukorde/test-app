package com.CarHub.repository.carRepository;

import com.CarHub.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c " +
            "JOIN c.brand b " +
            "JOIN c.transmission t " +
            "JOIN c.model m " +
            "JOIN c.year y " +
            "JOIN c.fuelType f " +
            "WHERE b.name LIKE %:details% " +
            "OR t.transmission LIKE %:details% " +
            "OR m.name LIKE %:details% " +
            "OR f.fuelType LIKE %:details% " +
            "OR CAST(y.year AS string) LIKE %:details%")
    List<Car> searchCar(@Param("details") String details);

}