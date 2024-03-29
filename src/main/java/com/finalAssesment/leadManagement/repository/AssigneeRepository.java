package com.finalAssesment.leadManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalAssesment.leadManagement.entity.AssigneeEntity;

public interface AssigneeRepository extends JpaRepository<AssigneeEntity, Long> {

  Optional<AssigneeEntity> findByAssigneeName(String assigneeName);

}
