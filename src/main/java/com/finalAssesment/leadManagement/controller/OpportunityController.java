package com.finalAssesment.leadManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finalAssesment.leadManagement.entity.OpportunityEntity;
import com.finalAssesment.leadManagement.repository.OpportunityRepository;

@RestController
public class OpportunityController {

  @Autowired
  private OpportunityRepository opportunityRepository;

  @PostMapping("/postOpportunity")
  public ResponseEntity<OpportunityEntity> postOpportunity(@RequestBody OpportunityEntity opportunity) {
    OpportunityEntity newOpportunity = opportunityRepository.save(opportunity);
    return new ResponseEntity<>(newOpportunity, HttpStatus.CREATED);
  }

  @GetMapping("/getallOpportunities")
  public ResponseEntity<List<OpportunityEntity>> getAllOpportunities() {
    List<OpportunityEntity> opportunities = opportunityRepository.findAll();
    return new ResponseEntity<>(opportunities, HttpStatus.OK);
  }

}
