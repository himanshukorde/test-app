package com.CarHub.controller;

import com.CarHub.entity.car.Car;
import com.CarHub.payload.AddCarDto;
import com.CarHub.service.CarRegistrationService;
import com.CarHub.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    private CarService carService;
    private final CarRegistrationService carRegistrationService;

    public CarController(CarService carService, CarRegistrationService carRegistrationService) {
        this.carService = carService;
        this.carRegistrationService = carRegistrationService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCar(@RequestBody AddCarDto dto){
        carService.addCar(dto);
        return new ResponseEntity<>("Car Added", HttpStatus.CREATED);
    }

    @PostMapping("/register-vin")
    public ResponseEntity<?> registerCarByVin(@RequestParam String vin) {
        try {
            Car savedCar = carRegistrationService.registerCarFromVin(vin);
            return ResponseEntity.ok(savedCar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to register car from VIN: " + e.getMessage());
        }
    }

}
