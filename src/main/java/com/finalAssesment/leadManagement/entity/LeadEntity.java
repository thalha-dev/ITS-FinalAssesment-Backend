package com.finalAssesment.leadManagement.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lead_entity")
public class LeadEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "lead_id")
  private Long id;
  // - leadcapturedby : string ( same as leadowner )
  @Column(name = "lead_captured_by")
  private String leadcapturedby;
  // - leadowner : string
  @Column(name = "lead_owner")
  private String leadowner;
  // - leadcapturedon : date
  @Column(name = "lead_captured_on")
  private Date leadcapturedon;
  // - urgent : string
  @Column(name = "lead_urgent")
  private String urgent;

  // FK

  // - leadsource : string
  @Column(name = "source_id")
  private Long sourceId;

  // - pointOfContact : string
  @Column(name = "lead_contact_id")
  private Long leadContactId;

  // - leadassignedto : string
  @Column(name = "assignee_id")
  private Long assigneeId;

  // - secassignee : string
  @Column(name = "second_assignee_id")
  private Long secondAssigneeId;

  // - opportunity : string
  @Column(name = "opportunity_type_id")
  private Long opportunityTypeId;

  // - orgName : string
  // - natureOfOrg : string
  @Column(name = "org_id")
  private Long orgId;

  // - domain : string
  @Column(name = "domain_id")
  private Long domainId;
}
