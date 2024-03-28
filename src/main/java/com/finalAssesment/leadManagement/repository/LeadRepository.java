package com.finalAssesment.leadManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalAssesment.leadManagement.entity.LeadEntity;

public interface LeadRepository extends JpaRepository<LeadEntity, Long> {

}
