
package com.jobtracker.dto;

public class DashboardDTO {

    private long totalApplications;
    private long interviewScheduled;
    private long selectedApplications;
    private long rejectedApplications;
    private long pendingApplications;

    public DashboardDTO(
            long totalApplications,
            long interviewScheduled,
            long selectedApplications,
            long rejectedApplications,
            long pendingApplications) {

        this.totalApplications = totalApplications;
        this.interviewScheduled = interviewScheduled;
        this.selectedApplications = selectedApplications;
        this.rejectedApplications = rejectedApplications;
        this.pendingApplications = pendingApplications;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public long getInterviewScheduled() {
        return interviewScheduled;
    }

    public long getSelectedApplications() {
        return selectedApplications;
    }

    public long getRejectedApplications() {
        return rejectedApplications;
    }

    public long getPendingApplications() {
        return pendingApplications;
    }
}
