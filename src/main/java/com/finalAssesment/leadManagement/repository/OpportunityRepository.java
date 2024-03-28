package com.finalAssesment.leadManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalAssesment.leadManagement.entity.OpportunityEntity;

public interface OpportunityRepository extends JpaRepository<OpportunityEntity, Long> {

}
