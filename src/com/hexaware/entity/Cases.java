package com.hexaware.entity;

public class Cases {
    private int caseID;
    private String caseDescription;
    private int incidentID;

    // Constructor
    public Cases() {

    }

    public Cases(int caseID, String caseDescription, int incidentID) {
        this.caseID = caseID;
        this.caseDescription = caseDescription;
        this.incidentID = incidentID;
    }

    // getters and setters
    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentsID) {
        this.incidentID = incidentsID;
    }

    @Override
    public String toString() {
        return "Cases [caseId=" + caseID + ", caseDescription=" + caseDescription +
                ",\nIncident ID =" + incidentID + "]\n";
    }

}