package com.finalAssesment.leadManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalAssesment.leadManagement.entity.OrganizationEntity;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

  Optional<OrganizationEntity> findByNatureofOrg(String natureofOrg);
}
