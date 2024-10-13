package dao;


import entity.Cases;
import entity.Incidents;
import entity.Reports;
import exception.CaseNotFoundException;
import exception.DatabaseException;
import exception.IncidentNumberNotFoundException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface ICrimeAnalysisService {
    // Create a new incident
    boolean createIncident(Incidents incident) throws DatabaseException;

    // Update the status of an incident
    boolean updateIncidentStatus(String status, int incidentId) throws DatabaseException;

    // Get a list of incidents within a date range
    Collection<Incidents> getIncidentsInDateRange(Date startDate, Date endDate) throws DatabaseException;

    // Search for incidents based on various criteria
    Collection<Incidents> searchIncidents(String criteria) throws DatabaseException;

    // Generate incident reports
    Reports generateIncidentReport(Incidents incident) throws IncidentNumberNotFoundException, DatabaseException;

    // Create a new case and associate it with incidents
    Cases createCase(int caseID,String caseDescription, int incidentid) throws DatabaseException;

    // Get details of a specific case
    Cases getCaseDetails(int caseId) throws CaseNotFoundException, DatabaseException;

    // Update case details
    boolean updateCaseDetails(Cases caseDetails) throws DatabaseException;

    // Get a list of all cases
    Collection<Cases> getAllCases() throws DatabaseException;
    
    void closeConnection() throws SQLException;
}
