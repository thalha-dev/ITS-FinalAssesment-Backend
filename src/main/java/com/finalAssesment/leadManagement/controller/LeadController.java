package com.finalAssesment.leadManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalAssesment.leadManagement.dto.LeadDto;
import com.finalAssesment.leadManagement.entity.AssigneeEntity;
import com.finalAssesment.leadManagement.entity.DomainEntity;
import com.finalAssesment.leadManagement.entity.LeadContactEntity;
import com.finalAssesment.leadManagement.entity.LeadEntity;
import com.finalAssesment.leadManagement.entity.OpportunityEntity;
import com.finalAssesment.leadManagement.entity.OrganizationEntity;
import com.finalAssesment.leadManagement.entity.SourceEntity;
import com.finalAssesment.leadManagement.repository.AssigneeRepository;
import com.finalAssesment.leadManagement.repository.DomainRepository;
import com.finalAssesment.leadManagement.repository.LeadContactRepository;
import com.finalAssesment.leadManagement.repository.LeadRepository;
import com.finalAssesment.leadManagement.repository.OpportunityRepository;
import com.finalAssesment.leadManagement.repository.OrganizationRepository;
import com.finalAssesment.leadManagement.repository.SourceRepository;

@RestController
public class LeadController {

  @Autowired
  private LeadRepository leadRepository;
  @Autowired
  private LeadContactRepository leadContactRepository;
  @Autowired
  private AssigneeRepository assigneeRepository;
  @Autowired
  private OpportunityRepository opportunityRepository;
  @Autowired
  private OrganizationRepository organizationRepository;
  @Autowired
  private DomainRepository domainRepository;
  @Autowired
  private SourceRepository sourceRepository;

  @PostMapping("/postDetails")
  public ResponseEntity<LeadDto> createNewLead(@RequestBody LeadDto lead) {
    LeadEntity newLead = new LeadEntity();
    newLead.setLeadcapturedby(lead.getLeadcapturedby());
    newLead.setLeadcapturedon(lead.getLeadcapturedon());
    newLead.setLeadowner(lead.getLeadowner());
    newLead.setUrgent(lead.getUrgent());
    newLead.setOrgName(lead.getOrgName());

    // Set foreign keys
    newLead.setSourceId(sourceRepository.findByLeadsource(lead.getLeadsource())
        .orElseGet(() -> {
          SourceEntity newSource = new SourceEntity();
          newSource.setLeadsource(lead.getLeadsource());
          sourceRepository.save(newSource);
          return newSource;
        }).getId());

    newLead.setLeadContactId(leadContactRepository.findByMobile(lead.getPointOfContact())
        .orElseGet(() -> {
          LeadContactEntity newLeadContact = new LeadContactEntity();
          newLeadContact.setMobile(lead.getMobile());
          newLeadContact.setLeadName(lead.getPointOfContact());
          newLeadContact.setEmail(lead.getEmailId());
          leadContactRepository.save(newLeadContact);
          return newLeadContact;
        }).getId());

    newLead.setAssigneeId(assigneeRepository.findByAssigneeName(lead.getLeadasignedto())
        .orElseGet(() -> {
          AssigneeEntity newAssignee = new AssigneeEntity();
          newAssignee.setAssigneeName(lead.getLeadasignedto());
          assigneeRepository.save(newAssignee);
          return newAssignee;
        }).getId());

    newLead.setSecondAssigneeId(assigneeRepository.findByAssigneeName(lead.getSecassignee())
        .orElseGet(() -> {
          AssigneeEntity newAssignee = new AssigneeEntity();
          newAssignee.setAssigneeName(lead.getSecassignee());
          assigneeRepository.save(newAssignee);
          return newAssignee;
        }).getId());

    newLead.setOpportunityTypeId(opportunityRepository.findByOpportunityType(lead.getOpportunity())
        .orElseGet(() -> {
          OpportunityEntity newOpportunity = new OpportunityEntity();
          newOpportunity.setOpportunityType(lead.getOpportunity());
          opportunityRepository.save(newOpportunity);
          return newOpportunity;
        }).getId());

    newLead.setOrgTypeId(organizationRepository.findByNatureofOrg(lead.getNatureOfOrg())
        .orElseGet(() -> {
          OrganizationEntity newOrganization = new OrganizationEntity();
          newOrganization.setNatureofOrg(lead.getNatureOfOrg());
          organizationRepository.save(newOrganization);
          return newOrganization;
        }).getId());

    newLead.setDomainId(domainRepository.findByDomainName(lead.getDomain())
        .orElseGet(() -> {
          DomainEntity newDomain = new DomainEntity();
          newDomain.setDomainName(lead.getDomain());
          domainRepository.save(newDomain);
          return newDomain;
        }).getId());

    leadRepository.save(newLead);

    return new ResponseEntity<>(lead, HttpStatus.CREATED);
  }

  @GetMapping("/getalldetails")
  public ResponseEntity<List<LeadDto>> getAllLeads() {
    List<LeadEntity> leads = leadRepository.findAll();
    List<LeadDto> leadDtos = new ArrayList<>();

    for (LeadEntity lead : leads) {
      LeadDto leadDto = new LeadDto();
      // map fields from LeadEntity to LeadDto
      leadDto.setId(lead.getId());
      leadDto.setPointOfContact(
          leadContactRepository.findById(lead.getLeadContactId()).map(LeadContactEntity::getLeadName).orElse(null));
      leadDto.setEmailId(
          leadContactRepository.findById(lead.getLeadContactId()).map(LeadContactEntity::getEmail).orElse(null));
      leadDto.setDomain(domainRepository.findById(lead.getDomainId()).map(DomainEntity::getDomainName).orElse(null));
      leadDto.setOrgName(lead.getOrgName());
      leadDto.setOpportunity(opportunityRepository.findById(lead.getOpportunityTypeId())
          .map(OpportunityEntity::getOpportunityType).orElse(null));
      leadDto.setNatureOfOrg(
          organizationRepository.findById(lead.getOrgTypeId()).map(OrganizationEntity::getNatureofOrg).orElse(null));
      leadDto.setLeadasignedto(
          assigneeRepository.findById(lead.getAssigneeId()).map(AssigneeEntity::getAssigneeName).orElse(null));
      leadDto.setLeadcapturedby(lead.getLeadcapturedby());
      leadDto.setLeadcapturedon(lead.getLeadcapturedon());
      leadDto
          .setLeadsource(sourceRepository.findById(lead.getSourceId()).map(SourceEntity::getLeadsource).orElse(null));
      leadDto.setLeadowner(lead.getLeadowner());
      leadDto.setSecassignee(
          assigneeRepository.findById(lead.getSecondAssigneeId()).map(AssigneeEntity::getAssigneeName).orElse(null));
      leadDto.setUrgent(lead.getUrgent());
      leadDto.setMobile(
          leadContactRepository.findById(lead.getLeadContactId()).map(LeadContactEntity::getMobile).orElse(null));

      leadDtos.add(leadDto);
    }

    return new ResponseEntity<>(leadDtos, HttpStatus.OK);
  }

  @GetMapping("/getdetails")
  public ResponseEntity<LeadDto> getSingleLead(@RequestParam("id") Long id) {
    Optional<LeadEntity> lead = leadRepository.findById(id);
    if (lead.isPresent()) {
      LeadEntity leadEntity = lead.get();
      LeadDto leadDto = new LeadDto();
      // map fields from LeadEntity to LeadDto
      leadDto.setId(leadEntity.getId());
      leadDto.setPointOfContact(leadContactRepository.findById(leadEntity.getLeadContactId())
          .map(LeadContactEntity::getLeadName).orElse(null));
      leadDto.setEmailId(
          leadContactRepository.findById(leadEntity.getLeadContactId()).map(LeadContactEntity::getEmail).orElse(null));
      leadDto
          .setDomain(domainRepository.findById(leadEntity.getDomainId()).map(DomainEntity::getDomainName).orElse(null));
      leadDto.setOrgName(leadEntity.getOrgName());
      leadDto.setOpportunity(opportunityRepository.findById(leadEntity.getOpportunityTypeId())
          .map(OpportunityEntity::getOpportunityType).orElse(null));
      leadDto.setNatureOfOrg(organizationRepository.findById(leadEntity.getOrgTypeId())
          .map(OrganizationEntity::getNatureofOrg).orElse(null));
      leadDto.setLeadasignedto(
          assigneeRepository.findById(leadEntity.getAssigneeId()).map(AssigneeEntity::getAssigneeName).orElse(null));
      leadDto.setLeadcapturedby(leadEntity.getLeadcapturedby());
      leadDto.setLeadcapturedon(leadEntity.getLeadcapturedon());
      leadDto.setLeadsource(
          sourceRepository.findById(leadEntity.getSourceId()).map(SourceEntity::getLeadsource).orElse(null));
      leadDto.setLeadowner(leadEntity.getLeadowner());
      leadDto.setSecassignee(assigneeRepository.findById(leadEntity.getSecondAssigneeId())
          .map(AssigneeEntity::getAssigneeName).orElse(null));
      leadDto.setUrgent(leadEntity.getUrgent());
      leadDto.setMobile(
          leadContactRepository.findById(leadEntity.getLeadContactId()).map(LeadContactEntity::getMobile).orElse(null));

      return new ResponseEntity<>(leadDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/editleads")
  public ResponseEntity<LeadDto> editSingleLead(@RequestParam("id") Long id, @RequestBody LeadDto lead) {
    Optional<LeadEntity> optionalLeadEntity = leadRepository.findById(id);
    if (optionalLeadEntity.isPresent()) {
      LeadEntity leadEntity = optionalLeadEntity.get();

      if (lead.getLeadcapturedby() != null) {
        leadEntity.setLeadcapturedby(lead.getLeadcapturedby());
        leadEntity.setLeadowner(lead.getLeadcapturedby());
      }

      if (lead.getLeadowner() != null) {
        leadEntity.setLeadcapturedby(lead.getLeadcapturedby());
        leadEntity.setLeadowner(lead.getLeadcapturedby());
      }

      if (lead.getLeadcapturedon() != null) {
        leadEntity.setLeadcapturedon(lead.getLeadcapturedon());
      }

      if (lead.getUrgent() != null) {
        leadEntity.setUrgent(lead.getUrgent());
      }

      if (lead.getOrgName() != null) {
        leadEntity.setOrgName(lead.getOrgName());
      }

      leadEntity = leadRepository.save(leadEntity);

      if (lead.getPointOfContact() != null) {
        Optional<LeadContactEntity> optionalLeadContactEntity = leadContactRepository
            .findById(leadEntity.getLeadContactId());
        LeadContactEntity leadContactEntity;
        if (optionalLeadContactEntity.isPresent()) {
          leadContactEntity = optionalLeadContactEntity.get();
        } else {
          leadContactEntity = new LeadContactEntity();
          leadContactEntity.setLeadName(lead.getPointOfContact());
        }
        leadContactEntity.setLeadName(lead.getPointOfContact());
        leadContactEntity = leadContactRepository.save(leadContactEntity);
        leadEntity.setLeadContactId(leadContactEntity.getId());
      }

      if (lead.getMobile() != null) {
        Optional<LeadContactEntity> optionalLeadContactEntity = leadContactRepository
            .findById(leadEntity.getLeadContactId());
        LeadContactEntity leadContactEntity;
        if (optionalLeadContactEntity.isPresent()) {
          leadContactEntity = optionalLeadContactEntity.get();
        } else {
          leadContactEntity = new LeadContactEntity();
          leadContactEntity.setMobile(lead.getMobile());
        }
        leadContactEntity.setMobile(lead.getMobile());
        leadContactEntity = leadContactRepository.save(leadContactEntity);
        leadEntity.setLeadContactId(leadContactEntity.getId());
      }

      if (lead.getEmailId() != null) {
        Optional<LeadContactEntity> optionalLeadContactEntity = leadContactRepository
            .findById(leadEntity.getLeadContactId());
        LeadContactEntity leadContactEntity;
        if (optionalLeadContactEntity.isPresent()) {
          leadContactEntity = optionalLeadContactEntity.get();
        } else {
          leadContactEntity = new LeadContactEntity();
          leadContactEntity.setEmail(lead.getEmailId());
        }
        leadContactEntity.setEmail(lead.getEmailId());
        leadContactEntity = leadContactRepository.save(leadContactEntity);
        leadEntity.setLeadContactId(leadContactEntity.getId());
      }

      if (lead.getDomain() != null) {
        Optional<DomainEntity> optionalDomainEntity = domainRepository.findByDomainName(lead.getDomain());
        DomainEntity domainEntity;
        if (optionalDomainEntity.isPresent()) {
          domainEntity = optionalDomainEntity.get();
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        leadEntity.setDomainId(domainEntity.getId());
      }

      if (lead.getOpportunity() != null) {
        Optional<OpportunityEntity> optionalOpportunityEntity = opportunityRepository
            .findByOpportunityType(lead.getOpportunity());
        OpportunityEntity opportunityEntity;
        if (optionalOpportunityEntity.isPresent()) {
          opportunityEntity = optionalOpportunityEntity.get();
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        leadEntity.setOpportunityTypeId(opportunityEntity.getId());
      }

      if (lead.getNatureOfOrg() != null) {
        Optional<OrganizationEntity> optionalOrganizationEntity = organizationRepository
            .findByNatureofOrg(lead.getNatureOfOrg());
        OrganizationEntity organizationEntity;
        if (optionalOrganizationEntity.isPresent()) {
          organizationEntity = optionalOrganizationEntity.get();
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        leadEntity.setOrgTypeId(organizationEntity.getId());
      }

      if (lead.getLeadsource() != null) {
        Optional<SourceEntity> optionalSourceEntity = sourceRepository.findByLeadsource(lead.getLeadsource());
        SourceEntity sourceEntity;
        if (optionalSourceEntity.isPresent()) {
          sourceEntity = optionalSourceEntity.get();
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        leadEntity.setSourceId(sourceEntity.getId());
      }

      if (lead.getLeadasignedto() != null) {
        Optional<AssigneeEntity> optionalAssigneeEntity = assigneeRepository
            .findByAssigneeName(lead.getLeadasignedto());
        AssigneeEntity assigneeEntity;
        if (optionalAssigneeEntity.isPresent()) {
          assigneeEntity = optionalAssigneeEntity.get();
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        leadEntity.setAssigneeId(assigneeEntity.getId());
      }

      if (lead.getSecassignee() != null) {
        Optional<AssigneeEntity> optionalSecondAssigneeEntity = assigneeRepository
            .findByAssigneeName(lead.getSecassignee());
        AssigneeEntity secondAssigneeEntity;
        if (optionalSecondAssigneeEntity.isPresent()) {
          secondAssigneeEntity = optionalSecondAssigneeEntity.get();
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        leadEntity.setSecondAssigneeId(secondAssigneeEntity.getId());
      }

      // Save the updated lead
      leadRepository.save(leadEntity);

      return new ResponseEntity<>(lead, HttpStatus.OK);

    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
