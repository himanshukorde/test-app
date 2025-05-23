package com.CarHub.repository.carEvaluationRepository;

import com.CarHub.entity.evaluation.CarDetailedEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDetailedEvaluationRepository extends JpaRepository<CarDetailedEvaluation, Long> {
}