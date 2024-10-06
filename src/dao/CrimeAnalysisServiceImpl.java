package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import util.DBConnection;
import entity.Cases;
import entity.Incidents;
import entity.Reports;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {

	private Connection connection;

	public CrimeAnalysisServiceImpl() throws ClassNotFoundException {
		this.connection = DBConnection.getConnection();
	}

	@Override
	public boolean createIncident(Incidents incident) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Incidents (IncidentType, IncidentDate, Location, Description, Status, VictimID, SuspectID) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, incident.getIncidentType());
			ps.setDate(2, new java.sql.Date(incident.getIncidentDate().getTime()));
			ps.setString(3, incident.getLocation());
			ps.setString(4, incident.getDescription());
			ps.setString(5, incident.getStatus());
			ps.setInt(6, incident.getVictimID());
			ps.setInt(7, incident.getSuspectID());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateIncidentStatus(String status, int incidentId) {
		String sql = "UPDATE Incidents SET Status = ? WHERE IncidentID = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, status);
			ps.setInt(2, incidentId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Collection<Incidents> getIncidentsInDateRange(Date startDate, Date endDate) {
		List<Incidents> incidents = new ArrayList<>();
        String sql = "SELECT * FROM Incidents WHERE IncidentDate BETWEEN ? AND ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                incidents.add(new Incidents(
                    rs.getInt("IncidentID"),
                    rs.getString("IncidentType"),
                    rs.getDate("IncidentDate"),
                    rs.getString("Location"),
                    rs.getString("Description"),
                    rs.getString("Status"),
                    rs.getInt("VictimID"),
                    rs.getInt("SuspectID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
	}

	@Override
	public Collection<Incidents> searchIncidents(String criteria) {
		
		Collection<Incidents> incidents = new ArrayList<>();
        String sql = "SELECT * FROM Incidents WHERE IncidentType LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + criteria + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                incidents.add(new Incidents(
                    rs.getInt("IncidentID"),
                    rs.getString("IncidentType"),
                    rs.getDate("IncidentDate"),
                    rs.getString("Location"),
                    rs.getString("Description"),
                    rs.getString("Status"),
                    rs.getInt("VictimID"),
                    rs.getInt("SuspectID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
	}

	@Override
	public Reports generateIncidentReport(Incidents incident) {
		
		String sql = "SELECT * FROM Reports WHERE incidentID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, incident.getIncidentID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	int reportID= rs.getInt("reportid");
            	int reportOfficer= rs.getInt("ReportingOfficer");
            	Date reportDate=rs.getDate("reportDate");
            	String ReportDetails = rs.getString("ReportDetails");
            	String Status = rs.getString("status");
                                
               
                return new Reports(reportID,incident.getIncidentID(), reportOfficer,reportDate,ReportDetails,Status); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public Cases createCase(int caseid,String caseDescription,int incidentid) {
		String sql = "INSERT INTO Cases (caseid,CaseDescription, incidentID) VALUES (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, caseid);
        	ps.setString(2, caseDescription);
        	ps.setInt(3, incidentid);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
            	
                    return new Cases(caseid, caseDescription, incidentid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public Cases getCaseDetails(int caseId) {
		String sql = "SELECT * FROM Cases WHERE CaseID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, caseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                String caseDescription = rs.getString("CaseDescription");
                int incidentID= rs.getInt("Caseid");                
               
                return new Cases(caseId, caseDescription,incidentID); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public boolean updateCaseDetails(Cases caseDetails) {
		String sql = "UPDATE Cases SET CaseDescription = ?,incidentid= ? WHERE CaseID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, caseDetails.getCaseDescription());
            ps.setInt(2, caseDetails.getIncidentID());
            ps.setInt(3, caseDetails.getCaseID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public Collection<Cases> getAllCases() {
		Collection<Cases> cases = new ArrayList<>();
        String sql = "SELECT * FROM Cases";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int caseId = rs.getInt("CaseID");
                String caseDescription = rs.getString("CaseDescription");
                int incidentid=rs.getInt("incidentid");
                cases.add(new Cases(caseId, caseDescription, incidentid)); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cases;
	}

}
