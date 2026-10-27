
package com.jobtracker.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.jobtracker.dto.DashboardDTO;

import com.jobtracker.entity.JobApplication;

import java.util.Map;

import java.time.LocalDate;

public interface JobApplicationService {

    JobApplication save(JobApplication application);

    List<JobApplication> findAll();

    JobApplication findbyId(Long id);

    JobApplication update(Long id, JobApplication application);

    void delete(Long id);

    // Search by company name
    List<JobApplication> searchByCompanyName(String companyName);

    // Filter by application status
    List<JobApplication> filterByStatus(String applicationStatus);
    
    Page<JobApplication> findAll(Pageable pageable);
    
    DashboardDTO getDashboardSummary();
    
    Map<String, Long> getApplicationStatusSummary();
    
    List<JobApplication> findApplicationsByDateRange(
            LocalDate startDate,
            LocalDate endDate);
}
