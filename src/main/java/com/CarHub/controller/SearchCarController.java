package com.CarHub.controller;

import com.CarHub.payload.CarResponseDTO;
import com.CarHub.repository.carRepository.CarRepository;
import com.CarHub.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchCarController {

    private CarRepository carRepository;
    private CarService carService;
    public SearchCarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/car")
    public List<CarResponseDTO> searchCar(@RequestParam String details){
        return carService.searchingCars(details);
    }
}
