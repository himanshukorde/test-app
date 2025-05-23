package com.CarHub.controller;

import com.CarHub.entity.evaluation.CarDetailedEvaluation;
import com.CarHub.entity.evaluation.CarEvaluationPhotos;
import com.CarHub.repository.carEvaluationRepository.CarDetailedEvaluationRepository;
import com.CarHub.repository.carEvaluationRepository.CarEvaluationPhotosRepository;
import com.CarHub.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluation-photos")
public class EvaluationPhotos {
    private S3Service s3Service;
    private CarEvaluationPhotosRepository evaluationPhotosRepository;
    private CarDetailedEvaluationRepository detailedEvaluationRepository;

    public EvaluationPhotos(S3Service s3Service, CarEvaluationPhotosRepository evaluationPhotosRepository, CarDetailedEvaluationRepository detailedEvaluationRepository) {
        this.s3Service = s3Service;
        this.evaluationPhotosRepository = evaluationPhotosRepository;

        this.detailedEvaluationRepository = detailedEvaluationRepository;
    }

    //http:localhost:8080/api/v1/images/upload/file/{bucketName}/car/{carId}
    @PostMapping(path = "/upload/file/{bucketName}/car/{carId}")
    public ResponseEntity<?> uploadFile(@RequestParam List<MultipartFile> files,
                                        @PathVariable String bucketName, @PathVariable long carId) {

        CarDetailedEvaluation car = detailedEvaluationRepository.findById(carId).get();
        for (MultipartFile file: files){
            String url = s3Service.uploadFile(file, bucketName);

            CarEvaluationPhotos evaluationPhotos = new CarEvaluationPhotos();
            evaluationPhotos.setCarDetailedEvaluation(car);
            evaluationPhotos.setUrl(url);
            evaluationPhotosRepository.save(evaluationPhotos);
        }
            return new ResponseEntity<>("Photos Uploaded", HttpStatus.OK);
    }
}
