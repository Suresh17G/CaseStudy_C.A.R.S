package dao;


import entity.Cases;
import entity.Incidents;
import entity.Reports;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface ICrimeAnalysisService {
    // Create a new incident
    boolean createIncident(Incidents incident);

    // Update the status of an incident
    boolean updateIncidentStatus(String status, int incidentId);

    // Get a list of incidents within a date range
    Collection<Incidents> getIncidentsInDateRange(Date startDate, Date endDate);

    // Search for incidents based on various criteria
    Collection<Incidents> searchIncidents(String criteria);

    // Generate incident reports
    Reports generateIncidentReport(Incidents incident);

    // Create a new case and associate it with incidents
    Cases createCase(int caseID,String caseDescription, int incidentid);

    // Get details of a specific case
    Cases getCaseDetails(int caseId);

    // Update case details
    boolean updateCaseDetails(Cases caseDetails);

    // Get a list of all cases
    Collection<Cases> getAllCases();
    
    void closeConnection() throws SQLException;
}
