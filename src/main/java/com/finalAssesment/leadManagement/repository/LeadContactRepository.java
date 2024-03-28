package com.finalAssesment.leadManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalAssesment.leadManagement.entity.LeadContactEntity;

public interface LeadContactRepository extends JpaRepository<LeadContactEntity, Long> {

}
