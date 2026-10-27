
package com.jobtracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobtracker.entity.JobApplication;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    // Get applications belonging to a particular user
    List<JobApplication> findByUserId(Long userId);

    // Find one application belonging to a particular user
    Optional<JobApplication> findByIdAndUserId(Long id, Long userId);

    // Search applications by company name for a particular user
    List<JobApplication> findByUserIdAndCompanyNameContainingIgnoreCase(
            Long userId, String companyName);

    // Filter applications by status for a particular user
    List<JobApplication> findByUserIdAndApplicationStatusIgnoreCase(
            Long userId, String applicationStatus);

    // Get paginated applications for a particular user
    Page<JobApplication> findByUserId(Long userId, Pageable pageable);

    // Count all applications belonging to a particular user
    long countByUserId(Long userId);

    // Count applications by status for a particular user
    long countByUserIdAndApplicationStatusIgnoreCase(
            Long userId, String applicationStatus);

    // Group applications by status for the dashboard
    @Query("SELECT j.applicationStatus, COUNT(j) " +
           "FROM JobApplication j " +
           "WHERE j.user.id = :userId " +
           "GROUP BY j.applicationStatus")
    List<Object[]> countApplicationsByUserIdGroupedByStatus(
            @Param("userId") Long userId);

    // Find applications within a date range for a particular user
    List<JobApplication> findByUserIdAndAppliedDateBetween(
            Long userId, LocalDate startDate, LocalDate endDate);
}
