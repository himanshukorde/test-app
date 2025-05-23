package com.CarHub.service;

import com.CarHub.entity.User;
import com.CarHub.entity.car.*;
import com.CarHub.payload.AddCarDto;
import com.CarHub.payload.CarResponseDTO;
import com.CarHub.repository.UserRepository;
import com.CarHub.repository.carRepository.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private ModelMapper modelMapper;
    private BrandRepository brandRepository;
    private FuelTypeRepository fuelTypeRepository;
    private ModelRepository modelRepository;
    private TransmissionRepository transmissionRepository;
    private CarRepository carRepository;
    private YearRepository yearRepository;
    private UserRepository userRepository;

    public CarService(ModelMapper modelMapper, BrandRepository brandRepository, FuelTypeRepository fuelTypeRepository,
                      ModelRepository modelRepository, TransmissionRepository transmissionRepository,
                      CarRepository carRepository,
                      YearRepository yearRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.modelRepository = modelRepository;
        this.transmissionRepository = transmissionRepository;
        this.carRepository = carRepository;
        this.yearRepository = yearRepository;
        this.userRepository = userRepository;
    }


    public void addCar(AddCarDto dto){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Brand brand = brandRepository.findByNameIgnoreCase(dto.getBrand()).orElseGet(() -> brandRepository.save(new Brand(dto.getBrand())));
        FuelType fuelType = fuelTypeRepository.findByFuelTypeIgnoreCase(dto.getFuelType()).orElseGet(() -> fuelTypeRepository.save(new FuelType(dto.getFuelType())));
        Model model = modelRepository.findByNameIgnoreCase(dto.getModel()).orElseGet(() -> modelRepository.save(new Model(dto.getModel())));
        Transmission transmission = transmissionRepository.findByTransmissionIgnoreCase(dto.getTransmission()).orElseGet(() -> transmissionRepository.save(new Transmission(dto.getTransmission())));
        Year year = yearRepository.findByYear(dto.getYear()).orElseGet(() -> yearRepository.save(new Year(dto.getYear())));
        Car carInfo = new Car();
        carInfo.setBrand(brand);
        carInfo.setFuelType(fuelType);
        carInfo.setModel(model);
        carInfo.setTransmission(transmission);
        carInfo.setYear(year);
        carInfo.setUser(loggedInUser);
//      carInfo.setStatus(CarStatus.PENDING);
        carRepository.save(carInfo);
    }

    public List<CarResponseDTO> searchingCars(String details){
        List<Car> cars = carRepository.searchCar(details);
        return cars.stream().map(car -> new CarResponseDTO(
                car.getId(),
                car.getBrand().getName(),
                car.getModel().getName(),
                car.getFuelType().getFuelType(),
                car.getTransmission().getTransmission(),
                car.getYear().getYear()
        )).collect(Collectors.toList());
    }
}
