package com.CarHub.repository.carEvaluationRepository;

import com.CarHub.entity.evaluation.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Long> {
  Area findByPinCode(String pinCode);
}