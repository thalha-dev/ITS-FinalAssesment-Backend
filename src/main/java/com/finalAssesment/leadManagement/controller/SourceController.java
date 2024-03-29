package com.finalAssesment.leadManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finalAssesment.leadManagement.entity.SourceEntity;
import com.finalAssesment.leadManagement.repository.SourceRepository;

@RestController
public class SourceController {

  @Autowired
  private SourceRepository sourceRepository;

  @PostMapping("/postSource")
  public ResponseEntity<SourceEntity> postSource(@RequestBody SourceEntity source) {
    SourceEntity newSource = sourceRepository.save(source);
    return new ResponseEntity<>(newSource, HttpStatus.CREATED);
  }

  @GetMapping("/getallLeadSources")
  public ResponseEntity<List<SourceEntity>> getAllSources() {
    List<SourceEntity> sources = sourceRepository.findAll();
    return new ResponseEntity<>(sources, HttpStatus.OK);
  }

}
