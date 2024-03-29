package com.finalAssesment.leadManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalAssesment.leadManagement.entity.DomainEntity;

public interface DomainRepository extends JpaRepository<DomainEntity, Long> {

  Optional<DomainEntity> findByDomainName(String domainName);
}
