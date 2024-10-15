package com.hexaware.entity;

import java.util.Date;

public class Incidents {
    private int incidentID;
    private String incidentType;
    private Date incidentDate;
    private String location;
    private String descriptions;
    private String status;
    private int victimID;
    private int suspectID;

//    Default constructor for Incidents.
    public Incidents() {

    }

    public Incidents(int incidentID, String incidentType, Date incidentDate, String location, String descriptions, String status, int victimID, int suspectID)
    {
        this.incidentID = incidentID;
        this.incidentType = incidentType;
        this.incidentDate = incidentDate;
        this.location = location;
        this.descriptions = descriptions;
        this.status = status;
        this.victimID = victimID;
        this.suspectID = suspectID;
    }


// getter and setter
    public int getIncidentID() {
        return incidentID;
    }
    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }
    public String getIncidentType() {
        return incidentType;
    }
    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }
    public Date getIncidentDate() {
        return incidentDate;
    }
    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDescription() {
        return descriptions;
    }
    public void setDescription(String descriptions) {
        this.descriptions = descriptions;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getVictimID() {
        return victimID;
    }
    public void setVictimID(int victimID) {
        this.victimID = victimID;
    }
    public int getSuspectID() {
        return suspectID;
    }
    public void setSuspectID(int suspectID) {
        this.suspectID = suspectID;
    }


    @Override
    public String toString() {
        return "Incident [incidentID=" + incidentID + ", incidentType=" + incidentType + ", incidentDate="
                + incidentDate + ", location=" + location + ", descriptions =" + descriptions + ", status=" + status
                + ", \nVictim ID =" + victimID + ", \nSuspect ID =" + suspectID + "]\n";
    }

}