
package com.jobtracker.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import com.jobtracker.dto.DashboardDTO;
import com.jobtracker.entity.JobApplication;
import com.jobtracker.service.JobApplicationService;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public JobApplication addApplication(
            @Valid @RequestBody JobApplication application) {
        return service.save(application);
    }

    @GetMapping
    public List<JobApplication> getAllApplication() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public JobApplication getApplicationById(@PathVariable Long id) {
        return service.findbyId(id);
    }

    @PutMapping("/{id}")
    public JobApplication updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody JobApplication application) {
        return service.update(id, application);
    }

    @DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable Long id) {
        service.delete(id);
        return "Job application deleted successfully";
    }

    @GetMapping("/search")
    public List<JobApplication> searchByCompanyName(
            @RequestParam String companyName) {
        return service.searchByCompanyName(companyName);
    }

    @GetMapping("/filter")
    public List<JobApplication> filterByStatus(
            @RequestParam String status) {
        return service.filterByStatus(status);
    }

    @GetMapping("/page")
    public Page<JobApplication> getApplicationsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        if (page < 0 || size < 1 || size > 100) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page must be non-negative and size must be between 1 and 100");
        }

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return service.findAll(pageable);
    }

    @GetMapping("/dashboard")
    public DashboardDTO getDashboardSummary() {
        return service.getDashboardSummary();
    }

    @GetMapping("/status-summary")
    public Map<String, Long> getApplicationStatusSummary() {
        return service.getApplicationStatusSummary();
    }

    @GetMapping("/date-range")
    public List<JobApplication> getApplicationsByDateRange(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Start date must not be after end date");
        }

        return service.findApplicationsByDateRange(startDate, endDate);
    }
}
