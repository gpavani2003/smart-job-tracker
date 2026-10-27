package com.jobtracker.entity;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.jobtracker.entity.UserAccount;


import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="job_application")
public class JobApplication {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

@NotBlank(message = "Company name is required")
private String companyName;

@NotBlank(message = "Job role is required")
private String jobRole;

private String jobLocation;
private String jobType;

@NotBlank(message = "Application status is required")
private String applicationStatus;

@NotNull(message = "Applied date is required")
private LocalDate appliedDate;

private LocalDate interviewDate;
private LocalDate followUpDate;


@Column(length=1000)
private String notes;


public JobApplication() {
	
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getCompanyName() {
	return companyName;
}

public void setCompanyName(String companyName) {
	this.companyName = companyName;
}

public String getJobRole() {
	return jobRole;
}

public void setJobRole(String jobRole) {
	this.jobRole = jobRole;
}

public String getJobLocation() {
	return jobLocation;
}

public void setJobLocation(String jobLocation) {
	this.jobLocation = jobLocation;
}

public String getJobType() {
	return jobType;
}

public void setJobType(String jobType) {
	this.jobType = jobType;
}

public String getApplicationStatus() {
	return applicationStatus;
}

public void setApplicationStatus(String applicationStatus) {
	this.applicationStatus = applicationStatus;
}

public LocalDate getAppliedDate() {
	return appliedDate;
}

public void setAppliedDate(LocalDate applicationDate) {
	this.appliedDate = applicationDate;
}

public LocalDate getInterviewDate() {
	return interviewDate;
}

public void setInterviewDate(LocalDate interviewDate) {
	this.interviewDate = interviewDate;
}

public LocalDate getFollowUpDate() {
	return followUpDate;
}

public void setFollowUpDate(LocalDate followUpDate) {
	this.followUpDate = followUpDate;
}

public String getNotes() {
	return notes;
}

public void setNotes(String notes) {
	this.notes = notes;
}
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "user_id", nullable = false)
private UserAccount user;

@JsonIgnore
public UserAccount getUser() {
    return user;
}

public void setUser(UserAccount user) {
    this.user = user;
}

}
