package com.hexaware.entity;

import java.util.Date;

public class Reports {
    private int reportID;
    private int incidentID;
    private int reportingOfficer;
    private Date reportDate;
    private String reportDetails;
    private String status;

    // Constructor
    public Reports(){

    }
    public Reports(int reportID,int incidentID,int reportingOfficer,Date reportDate,String reportDetails,String status){
        this.reportID = reportID;
        this.incidentID = incidentID;
        this.reportingOfficer = reportingOfficer;
        this.reportDate = reportDate;
        this.reportDetails = reportDetails;
        this.status = status;

    }

    // getters and setters
    public int getReportID() {
        return reportID;
    }
    public void setReportID(int reportID) {
        this.reportID = reportID;
    }
    public int getIncidentID() {
        return incidentID;
    }
    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }
    public int getReportingOfficer() {
        return reportingOfficer;
    }
    public void setReportingOfficer(int reportingOfficer) {
        this.reportingOfficer = reportingOfficer;
    }
    public Date getReportDate() {
        return reportDate;
    }
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
    public String getReportDetails() {
        return reportDetails;
    }
    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reports [reportId=" + reportID + ", incident ID=" + incidentID + ", reportingOfficer=" + reportingOfficer
                + ", reportDate=" + reportDate + ", reportDetails=" + reportDetails + ", report Status=" + status + "]";
    }
}