package com.finalAssesment.leadManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalAssesment.leadManagement.entity.LeadContactEntity;

public interface LeadContactRepository extends JpaRepository<LeadContactEntity, Long> {

  Optional<LeadContactEntity> findByMobile(String mobile);
}
