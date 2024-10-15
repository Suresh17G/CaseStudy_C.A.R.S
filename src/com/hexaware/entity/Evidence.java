package com.hexaware.entity;

public class Evidence {
    private int evidenceID;
    private String description;
    private String locationFound;
    private int incidentID;

    // Constructor
    public Evidence(){

    }

    public Evidence(int evidenceID,String description,String locationFound,int incidentID){
        this.evidenceID = evidenceID;
        this.description = description;
        this.locationFound = locationFound;
        this.incidentID = incidentID;
    }

    // getters and setters
    public int getEvidenceID() {
        return evidenceID;
    }
    public void setEvidenceID(int evidenceID) {
        this.evidenceID = evidenceID;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLocationFound() {
        return locationFound;
    }
    public void setLocationFound(String locationFound) {
        this.locationFound = locationFound;
    }
    public int getIncidentID() {
        return incidentID;
    }
    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    @Override
    public String toString() {
        return "Evidence [evidenceId=" + evidenceID + ", description=" + description +
                ", locationFound=" + locationFound + ", incidentId=" + incidentID + "]";
    }
}