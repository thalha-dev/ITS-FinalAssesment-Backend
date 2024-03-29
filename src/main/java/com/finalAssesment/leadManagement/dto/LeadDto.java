package com.finalAssesment.leadManagement.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeadDto {
  private Long id;
  private String pointOfContact;
  private String emailId;
  private String domain;
  private String orgName;
  private String opportunity;
  private String natureOfOrg;
  private String leadasignedto;
  private String leadcapturedby;
  private Date leadcapturedon;
  private String leadsource;
  private String leadowner;
  private String secassignee;
  private String urgent;
  private String mobile;
}
