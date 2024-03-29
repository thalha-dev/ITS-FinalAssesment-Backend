package com.finalAssesment.leadManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finalAssesment.leadManagement.entity.DomainEntity;
import com.finalAssesment.leadManagement.repository.DomainRepository;

@RestController
public class DomainController {

  @Autowired
  private DomainRepository domainRepository;

  @PostMapping("/postDomain")
  public ResponseEntity<DomainEntity> postDomain(@RequestBody DomainEntity domain) {
    DomainEntity newDomain = domainRepository.save(domain);
    return new ResponseEntity<>(newDomain, HttpStatus.CREATED);
  }

  @GetMapping("/getalldomains")
  public ResponseEntity<List<DomainEntity>> getAllDomains() {
    List<DomainEntity> domains = domainRepository.findAll();
    return new ResponseEntity<>(domains, HttpStatus.OK);
  }

}
