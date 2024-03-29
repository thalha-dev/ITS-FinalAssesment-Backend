package com.finalAssesment.leadManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finalAssesment.leadManagement.entity.AssigneeEntity;
import com.finalAssesment.leadManagement.repository.AssigneeRepository;

@RestController
public class AssigneeController {

  @Autowired
  private AssigneeRepository assigneeRepository;

  @PostMapping("/postAssignees")
  public ResponseEntity<AssigneeEntity> addNewAssignee(@RequestBody AssigneeEntity assignee) {
    AssigneeEntity newAssignee = new AssigneeEntity();
    newAssignee.setAssigneeName(assignee.getAssigneeName());
    newAssignee = assigneeRepository.save(newAssignee);
    return new ResponseEntity<>(newAssignee, HttpStatus.CREATED);
  }

  @GetMapping("/getallAssignees")
  public ResponseEntity<List<AssigneeEntity>> getAllAssignees() {
    List<AssigneeEntity> assignees = assigneeRepository.findAll();
    return new ResponseEntity<>(assignees, HttpStatus.OK);
  }

}
