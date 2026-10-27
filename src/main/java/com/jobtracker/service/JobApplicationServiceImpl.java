package com.jobtracker.service;

import java.util.List;
import com.jobtracker.exception.JobApplicationNotFoundException;

import org.springframework.stereotype.Service;

import com.jobtracker.entity.JobApplication;
import com.jobtracker.repository.JobApplicationRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobtracker.dto.DashboardDTO;

import java.util.LinkedHashMap;
import java.util.Map;

import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.jobtracker.entity.UserAccount;
import com.jobtracker.repository.UserAccountRepository;

@Service
public class JobApplicationServiceImpl implements JobApplicationService{
private final JobApplicationRepository repository;

private UserAccountRepository userAccountRepository;

public JobApplicationServiceImpl(
        JobApplicationRepository repository,
        UserAccountRepository userAccountRepository) {
    this.repository = repository;
    this.userAccountRepository = userAccountRepository;
}

@Override
public JobApplication save(JobApplication application) {
    application.setUser(getLoggedInUser());
    return repository.save(application);
}

@Override
public List<JobApplication> findAll() {
    return repository.findByUserId(getLoggedInUser().getId());
}


@Override
public JobApplication findbyId(Long id) {
    return repository.findByIdAndUserId(
            id, getLoggedInUser().getId())
            .orElseThrow(() -> new JobApplicationNotFoundException(
                    "Job application not found with id: " + id));
}


	public JobApplication update(Long id, JobApplication updatedApplication) {
	    JobApplication existing = findbyId(id);

	    existing.setCompanyName(updatedApplication.getCompanyName());
	    existing.setJobRole(updatedApplication.getJobRole());
	    existing.setJobLocation(updatedApplication.getJobLocation());
	    existing.setJobType(updatedApplication.getJobType());
	    existing.setApplicationStatus(updatedApplication.getApplicationStatus());
	    existing.setAppliedDate(updatedApplication.getAppliedDate());
	    existing.setInterviewDate(updatedApplication.getInterviewDate());
	    existing.setFollowUpDate(updatedApplication.getFollowUpDate());
	    existing.setNotes(updatedApplication.getNotes());

	    return repository.save(existing);
	}

    @Override
    public void delete(Long id) {
     JobApplication existing = findbyId(id);
     repository.delete(existing);
}

    @Override
    public List<JobApplication> searchByCompanyName(String companyName) {
        return repository.findByUserIdAndCompanyNameContainingIgnoreCase(
                getLoggedInUser().getId(), companyName);
    }

    @Override
    public List<JobApplication> filterByStatus(String applicationStatus) {
        return repository.findByUserIdAndApplicationStatusIgnoreCase(
                getLoggedInUser().getId(), applicationStatus);
    }

    @Override
    public Page<JobApplication> findAll(Pageable pageable) {
        return repository.findByUserId(
                getLoggedInUser().getId(), pageable);
    }
    @Override
    public DashboardDTO getDashboardSummary() {
        Long userId = getLoggedInUser().getId();

        long total = repository.countByUserId(userId);
        long interviews = repository.countByUserIdAndApplicationStatusIgnoreCase(
                userId, "Interview Scheduled");
        long offers = repository.countByUserIdAndApplicationStatusIgnoreCase(
                userId, "Offer Received");
        long rejected = repository.countByUserIdAndApplicationStatusIgnoreCase(
                userId, "Rejected");
        long pending = repository.countByUserIdAndApplicationStatusIgnoreCase(
                userId, "Applied");

        return new DashboardDTO(total, interviews, offers, rejected, pending);
    }
    @Override
    public Map<String, Long> getApplicationStatusSummary() {
        Long userId = getLoggedInUser().getId();

        List<Object[]> results =
                repository.countApplicationsByUserIdGroupedByStatus(userId);

        Map<String, Long> summary = new LinkedHashMap<>();

        for (Object[] row : results) {
            summary.put((String) row[0], (Long) row[1]);
        }

        return summary;
    }
    
    @Override
    public List<JobApplication> findApplicationsByDateRange(
            LocalDate startDate, LocalDate endDate) {
        return repository.findByUserIdAndAppliedDateBetween(
                getLoggedInUser().getId(), startDate, endDate);
    }
    private UserAccount getLoggedInUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return userAccountRepository.findByEmail(authentication.getName())
                .orElseThrow(() ->
                        new RuntimeException("Logged-in user not found"));
    }
    
}
