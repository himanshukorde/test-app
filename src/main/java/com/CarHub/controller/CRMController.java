package com.CarHub.controller;

import com.CarHub.entity.evaluation.Agent;
import com.CarHub.entity.evaluation.Area;
import com.CarHub.entity.evaluation.CustomerVisit;
import com.CarHub.repository.carEvaluationRepository.AgentRepository;
import com.CarHub.repository.carEvaluationRepository.AreaRepository;
import com.CarHub.repository.carEvaluationRepository.CustomerVisitRepository;
import com.CarHub.service.TwilioSmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crm")
public class CRMController {

    private AreaRepository areaRepository;
    private final AgentRepository agentRepository;
    private CustomerVisitRepository customerVisitRepository;
    private TwilioSmsService smsService;

    public CRMController(AreaRepository areaRepository,
                         AgentRepository agentRepository, CustomerVisitRepository customerVisitRepository, TwilioSmsService smsService) {
        this.areaRepository = areaRepository;
        this.agentRepository = agentRepository;
        this.customerVisitRepository = customerVisitRepository;
        this.smsService = smsService;
    }

    @GetMapping("/getAgent/{pinCode}")
    public ResponseEntity<Area> getAgentDetails(@PathVariable String pinCode){
        Area agentDetails = areaRepository.findByPinCode(pinCode);
        return new ResponseEntity<>(agentDetails, HttpStatus.OK);

    }

    @PutMapping("/allocate-agent/{customerId}/{agentId}")
    public ResponseEntity<?> allocateAgent(@PathVariable long customerId, @PathVariable long agentId){
        Optional<Agent> opAgent = agentRepository.findById(agentId);
        Agent agent = null;
        if(opAgent.isPresent()){
            agent = opAgent.get();
        }
        CustomerVisit customerVisit = customerVisitRepository.findById(customerId).get();
        customerVisit.setAgent(agent);
        customerVisitRepository.save(customerVisit);
        smsService.sendSms("+917391942730", "Agent is Allocate" + agent.getMobile());
        return new ResponseEntity<>("Agent Allocated", HttpStatus.OK);
    }
}
