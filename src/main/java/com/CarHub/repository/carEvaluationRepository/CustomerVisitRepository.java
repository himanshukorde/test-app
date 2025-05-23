package com.CarHub.repository.carEvaluationRepository;

import com.CarHub.entity.evaluation.CustomerVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerVisitRepository extends JpaRepository<CustomerVisit, Long> {
}