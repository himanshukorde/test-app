package com.CarHub.controller;

import com.CarHub.entity.car.Car;
import com.CarHub.entity.car.CarImages;
import com.CarHub.payload.CarResponseDTO;
import com.CarHub.payload.ImageResponseDto;
import com.CarHub.repository.carRepository.CarRepository;
import com.CarHub.repository.imageRepository.CarImagesRepository;
import com.CarHub.service.S3Service;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class FileUploadController {
    private S3Service s3Service;
    private CarRepository carRepository;
    private CarImagesRepository imagesRepository;
    private ModelMapper mapper;


    public FileUploadController(S3Service s3Service, CarRepository carRepository, CarImagesRepository imagesRepository, ModelMapper mapper) {
        this.s3Service = s3Service;
        this.carRepository = carRepository;
        this.imagesRepository = imagesRepository;
        this.mapper = mapper;
    }

    //http://localhost:8080/api/v1/images/upload/file/{bucketName}/car/{carId}
    @PostMapping(path = "/upload/file/{bucketName}/car/{carId}")
    public ResponseEntity<ImageResponseDto> uploadFile(@RequestParam MultipartFile file, @PathVariable String bucketName, @PathVariable Long carId) {
        String url = s3Service.uploadFile(file, bucketName);
        Car car = carRepository.findById(carId).orElseThrow(()-> new RuntimeException("Car not Found" + carId));
        CarImages carImages = new CarImages();
        carImages.setUrl(url);
        carImages.setCar(car);
        CarImages saved = imagesRepository.save(carImages);
        ImageResponseDto imageResponse = mapper.map(saved, ImageResponseDto.class);
        imageResponse.setId(saved.getId());
        imageResponse.setUrl(saved.getUrl());
        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }
}