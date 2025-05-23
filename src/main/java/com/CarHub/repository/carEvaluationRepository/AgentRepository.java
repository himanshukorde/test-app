package com.CarHub.repository.carEvaluationRepository;

import com.CarHub.entity.evaluation.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}