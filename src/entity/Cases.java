package entity;

public class Cases {
    private int caseID;
    private String caseDescription;
    private int incidentsID;

    // Constructor
    public Cases() {

    }

    public Cases(int caseID, String caseDescription, int incidentsID) {
        this.caseID = caseID;
        this.caseDescription = caseDescription;
        this.incidentsID = incidentsID;
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

    public int getIncidentsID() {
        return incidentsID;
    }

    public void setIncidentsID(int incidentsID) {
        this.incidentsID = incidentsID;
    }

    @Override
    public String toString() {
        return "Cases [caseId=" + caseID + ", caseDescription=" + caseDescription +
                ",\nIncident ID =" + incidentsID + "]\n";
    }

}