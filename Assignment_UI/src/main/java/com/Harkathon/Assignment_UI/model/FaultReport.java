package com.Harkathon.Assignment_UI.model;

public class FaultReport {

    private Long id;
    private String description;
    private String severity;
    private String reportedBy;

    public FaultReport(Long id, String description, String severity, String reportedBy) {
        this.id = id;
        this.description = description;
        this.severity = severity;
        this.reportedBy = reportedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }
}