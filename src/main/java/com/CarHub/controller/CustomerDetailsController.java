package com.CarHub.controller;

import com.CarHub.entity.evaluation.CustomerVisit;
import com.CarHub.payload.CustomerVisitDto;
import com.CarHub.repository.carEvaluationRepository.CustomerVisitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer-details")
public class CustomerDetailsController {

    private ModelMapper mapper;
    private final CustomerVisitRepository customerVisitRepository;

    public CustomerDetailsController(ModelMapper mapper,CustomerVisitRepository customerVisitRepository) {
        this.mapper = mapper;
        this.customerVisitRepository = customerVisitRepository;
    }

    @PostMapping
    public ResponseEntity<?> customerDetails(@RequestBody CustomerVisitDto dto){
        CustomerVisit customerDetails = mapper.map(dto, CustomerVisit.class);
        customerVisitRepository.save(customerDetails);
        return new ResponseEntity<>("Saved", HttpStatus.CREATED);
    }
}
