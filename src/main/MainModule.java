package main;

import dao.CrimeAnalysisServiceImpl;
import dao.ICrimeAnalysisService;
import entity.Cases;
import entity.Incidents;
import entity.Reports;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class MainModule {


    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ICrimeAnalysisService service= new CrimeAnalysisServiceImpl();
        System.out.println("Welcome to Crime Analysis and Reporting System\n");
        try {
        while (true) {
            System.out.println();
            System.out .println("1. Create Incident");
            System.out.println("2. Update Incident Status");
            System.out.println("3. Get Incidents in Date Range");
            System.out.println("4. Search Incidents");
            System.out.println("5. Generate Incident Report");
            System.out.println("6. Create Case");
            System.out.println("7. Get Case Details");
            System.out.println("8. Update Case Details");
            System.out.println("9. Get All Cases");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createIncident(service);
                    break;
                case 2:
                    updateIncidentStatus(service);
                    break;
                case 3:
                    getIncidentsInDateRange(service);
                    break;
                case 4:
                    searchIncidents(service);
                    break;
                case 5:
                    generateIncidentReport(service);
                    break;
                case 6:
                    createCase(service);
                    break;
                case 7:
                    getCaseDetails(service);
                    break;
                case 8:
                    updateCaseDetails(service);
                    break;
                case 9:
                    getAllCases(service);
                    break;
                case 10:
                    System.out.println("Exiting...");
                    service.closeConnection();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }catch(Exception e) {
    	System.out.println("\nUnexpected Error: " + e + "\nExiting Crime Analysis and Reporting System!!");
    }

    }
    private static void createIncident(ICrimeAnalysisService service) {
        Incidents incident = new Incidents();
        System.out.print("Enter incident ID: ");
        incident.setIncidentID(scanner.nextInt());
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter incident type: ");
        incident.setIncidentType(scanner.nextLine());
        System.out.print("Enter incident date (yyyy-MM-dd): ");
        String incidentDateStr = scanner.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate incidentDate = LocalDate.parse(incidentDateStr, formatter);
            java.sql.Date sqlDate = java.sql.Date.valueOf(incidentDate);
            incident.setIncidentDate(sqlDate);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }
        System.out.print("Enter location: ");
        incident.setLocation(scanner.nextLine());
        System.out.print("Enter description: ");
        incident.setDescription(scanner.nextLine());
        System.out.print("Enter status: ");
        incident.setStatus(scanner.next());
        System.out.print("Enter victim ID: ");
        incident.setVictimID(scanner.nextInt());
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter suspect ID: ");
        incident.setSuspectID(scanner.nextInt());
        scanner.nextLine(); // Consume the newline character

        if (service.createIncident(incident)) {
            System.out.println("Incident created successfully.");
        } else {
            System.out.println("Failed to create incident.");
        }
    }

    private static void updateIncidentStatus(ICrimeAnalysisService service) {
        System.out.print("Enter incident ID: ");
        int incidentId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter new status: ");
        String status = scanner.nextLine();

        if (service.updateIncidentStatus(status, incidentId)) {
            System.out.println("Incident status updated successfully.");
        } else {
            System.out.println("Failed to update incident status.");
        }
    }

    private static void getIncidentsInDateRange(ICrimeAnalysisService service) {
        System.out.print("Enter start date (yyyy-MM-dd): ");
        String startDateStr = scanner.next();
        System.out.print("Enter end date (yyyy-MM-dd): ");
        String endDateStr = scanner.next();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);
            java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
            java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);
            Collection<Incidents> incidents = service.getIncidentsInDateRange(sqlStartDate,sqlEndDate);

            if (incidents.isEmpty()) {
                System.out.println("No incidents found in the given date range.");
            } else {
                for (Incidents incident : incidents) {
                    System.out.println("Incident ID: " + incident.getIncidentID());
                    System.out.println("Incident Type: " + incident.getIncidentType());
                    System.out.println("Incident Date: " + incident.getIncidentDate());
                    System.out.println("Location: " + incident.getLocation());
                    System.out.println("Description: " + incident.getDescription());
                    System.out.println("Status: " + incident.getStatus());
                    System.out.println("Victim ID: " + incident.getVictimID());
                    System.out.println("Suspect ID: " + incident.getSuspectID());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void searchIncidents(ICrimeAnalysisService service) {
        System.out.print("Enter Incident type to search: ");
        String keyword = scanner.next();

        Collection<Incidents> incidents = service.searchIncidents(keyword);

        if (incidents.isEmpty()) {
            System.out.println("No incidents found matching the search keyword.");
        } else {
            for (Incidents incident : incidents) {
            	System.out.println();
                System.out.println("Incident ID: " + incident.getIncidentID());
                System.out.println("Incident Type: " + incident.getIncidentType());
                System.out.println("Incident Date: " + incident.getIncidentDate());
                System.out.println("Location: " + incident.getLocation());
                System.out.println("Description: " + incident.getDescription());
                System.out.println("Status: " + incident.getStatus());
                System.out.println("Victim ID: " + incident.getVictimID());
                System.out.println("Suspect ID: " + incident.getSuspectID());
                System.out.println();
            }
        }
    }

    private static void generateIncidentReport(ICrimeAnalysisService service) {
        System.out.print("Enter incident ID: ");
        int incidentId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Incidents incident = new Incidents();
        incident.setIncidentID(incidentId);

        Reports report = service.generateIncidentReport(incident);

        if (report != null) {
            System.out.println("\nIncident Report:");
            System.out.println();
            System.out.println("Report ID: " + report.getReportID());
            System.out.println("Incident ID: " + report.getIncidentID());
            System.out.println("Reporting Officer: " + report.getReportingOfficer());
            System.out.println("Report Date: " + report.getReportDate());
            System.out.println("Report Details: " + report.getReportDetails());
            System.out.println("Status: " + report.getStatus());
            System.out.println();
        } else {
            System.out.println("Failed to generate incident report.");
        }
    }

    private static void createCase(ICrimeAnalysisService service) {

        System.out.print("Enter CaseID: ");
        int caseID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter case description: ");
        String caseDescription = scanner.nextLine();
        System.out.print("Enter incident ID : ");
        int incidentId = scanner.nextInt();
        Cases caseObj = service.createCase(caseID,caseDescription, incidentId);

        if (caseObj != null) {
            System.out.println("Case created successfully.");
            System.out.println("\nCase ID: " + caseObj.getCaseID());
            System.out.println("Case Description: " + caseObj.getCaseDescription());
            System.out.println("Incident IDs: "+caseObj.getIncidentID());
        } else {
            System.out.println("Failed to create case.");
        }
    }

    private static void getCaseDetails(ICrimeAnalysisService service) {
        System.out.print("Enter case ID: ");
        int caseId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Cases caseObj = service.getCaseDetails(caseId);

        if (caseObj != null) {
            System.out.println("\nCase Details:");
            System.out.println();
            System.out.println("Case ID: " + caseObj.getCaseID());
            System.out.println("Case Description: " + caseObj.getCaseDescription());
            System.out.println("Incident ID: " + caseObj.getIncidentID());
        } else {
            System.out.println("Failed to retrieve case details.");
        }
    }

    private static void updateCaseDetails(ICrimeAnalysisService service) {
        System.out.print("Enter case ID: ");
        int caseId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("Enter Incident ID: ");
        int incidentID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new case description: ");
        String caseDescription = scanner.nextLine();

        Cases caseObj = new Cases();
        caseObj.setCaseID(caseId);
        caseObj.setIncidentID(incidentID);
        caseObj.setCaseDescription(caseDescription);

        if (service.updateCaseDetails(caseObj)) {
            System.out.println("Case details updated successfully.");
        } else {
            System.out.println("Failed to update case details.");
        }
    }

    private static void getAllCases(ICrimeAnalysisService service) throws ClassNotFoundException {
        Collection<Cases> cases = service.getAllCases();

        if (cases.isEmpty()) {
            System.out.println("No cases found.");
        } else {
            for (Cases caseObj : cases) {
                System.out.println("Case ID: " + caseObj.getCaseID());
                System.out.println("Case Description: " + caseObj.getCaseDescription());
                System.out.println("Incident ID: " + caseObj.getIncidentID());
                System.out.println();
            }
        }
    }

}