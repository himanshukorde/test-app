package com.CarHub.service;

import com.CarHub.entity.car.*;
import com.CarHub.repository.carRepository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CarRegistrationService {

    @Autowired
    private VINService vinService;
    @Autowired private CarRepository carRepo;
    @Autowired private BrandRepository brandRepo;
    @Autowired private ModelRepository modelRepo;
    @Autowired private FuelTypeRepository fuelTypeRepo;
    @Autowired private TransmissionRepository transmissionRepo;
    @Autowired private YearRepository yearRepo;

    public Car registerCarFromVin(String vin) {
        Map<String, String> decoded = vinService.decodeVin(vin);

        Brand brand = brandRepo.findByName(decoded.get("brand"))
                .orElseGet(() -> brandRepo.save(new Brand(decoded.get("brand"))));

        Model model = modelRepo.findByName(decoded.get("model"))
                .orElseGet(() -> modelRepo.save(new Model(decoded.get("model"))));

        FuelType fuelType = fuelTypeRepo.findByFuelType(decoded.get("fuelType"))
                .orElseGet(() -> fuelTypeRepo.save(new FuelType(decoded.get("fuelType"))));

        Transmission transmission = transmissionRepo.findByTransmission(decoded.get("transmission"))
                .orElseGet(() -> transmissionRepo.save(new Transmission(decoded.get("transmission"))));

        Year year = yearRepo.findByYear(Integer.parseInt(decoded.get("year")))
                .orElseGet(() -> yearRepo.save(new Year(Integer.parseInt(decoded.get("year")))));

        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setFuelType(fuelType);
        car.setTransmission(transmission);
        car.setYear(year);

        return carRepo.save(car);
    }
}

