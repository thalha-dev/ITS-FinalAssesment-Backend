package com.finalAssesment.leadManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalAssesment.leadManagement.entity.AssigneeEntity;

public interface AssigneeRepository extends JpaRepository<AssigneeEntity, Long> {

}
