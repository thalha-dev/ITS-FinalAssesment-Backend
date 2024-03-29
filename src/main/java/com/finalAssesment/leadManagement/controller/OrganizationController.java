package com.finalAssesment.leadManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finalAssesment.leadManagement.entity.OrganizationEntity;
import com.finalAssesment.leadManagement.repository.OrganizationRepository;

@RestController
public class OrganizationController {

  @Autowired
  private OrganizationRepository organizationRepository;

  @PostMapping("/postOrg")
  public ResponseEntity<OrganizationEntity> postOrg(@RequestBody OrganizationEntity org) {
    OrganizationEntity newOrg = organizationRepository.save(org);
    return new ResponseEntity<>(newOrg, HttpStatus.CREATED);
  }

  @GetMapping("/getorganisation")
  public ResponseEntity<List<OrganizationEntity>> getAllOrgs() {
    List<OrganizationEntity> organisations = organizationRepository.findAll();
    return new ResponseEntity<>(organisations, HttpStatus.OK);
  }

}
