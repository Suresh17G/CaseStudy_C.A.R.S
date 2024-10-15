package com.hexaware.entity;

public class LawEnforcementAgencies {
    private int agencyID;
    private String agencyName;
    private String jurisdiction;
    private String contactInformation;

    // Constructor
    public LawEnforcementAgencies(){

    }
    public LawEnforcementAgencies(int agencyID,String agencyName,String jurisdiction,String contactInformation){
        this.agencyID = agencyID;
        this.agencyName = agencyName;
        this.jurisdiction = jurisdiction;
        this.contactInformation = contactInformation;
    }

    // getters and setters
    public int getAgencyID() {
        return agencyID;
    }
    public void setAgencyID(int agencyID) {
        this.agencyID = agencyID;
    }
    public String getAgencyName() {
        return agencyName;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    public String getJurisdiction() {
        return jurisdiction;
    }
    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
    public String getContactInformation() {
        return contactInformation;
    }
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "LawEnforcementAgencies [agencyId=" + agencyID + ", agencyName=" + agencyName
                + ", jurisdiction=" + jurisdiction + ", contactInformation=" + contactInformation + "]";
    }

}