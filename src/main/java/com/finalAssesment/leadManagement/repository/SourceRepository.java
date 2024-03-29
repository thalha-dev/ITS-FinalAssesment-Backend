package com.finalAssesment.leadManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalAssesment.leadManagement.entity.SourceEntity;

public interface SourceRepository extends JpaRepository<SourceEntity, Long> {

  Optional<SourceEntity> findByLeadsource(String leadsource);
}
