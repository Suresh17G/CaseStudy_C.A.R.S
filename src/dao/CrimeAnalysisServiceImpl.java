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
import exception.CaseNotFoundException;
import exception.DatabaseException;
import exception.IncidentNumberNotFoundException;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {

	private Connection connection;

	public CrimeAnalysisServiceImpl() throws ClassNotFoundException {
		this.connection = DBConnection.getConnection();
	}

	@Override
	public boolean createIncident(Incidents incident) throws DatabaseException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Incidents (incidentID,IncidentType, IncidentDate, Location, Description, Status, VictimID, SuspectID) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, incident.getIncidentID());
			ps.setString(2, incident.getIncidentType());
			ps.setDate(3, new java.sql.Date(incident.getIncidentDate().getTime()));
			ps.setString(4, incident.getLocation());
			ps.setString(5, incident.getDescription());
			ps.setString(6, incident.getStatus());
			ps.setInt(7, incident.getVictimID());
			ps.setInt(8, incident.getSuspectID());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DatabaseException("Error creating incident: " + e.getMessage());
			
		}
	}

	@Override
	public boolean updateIncidentStatus(String status, int incidentId) throws DatabaseException {
		String sql = "UPDATE Incidents SET Status = ? WHERE IncidentID = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, status);
			ps.setInt(2, incidentId);
			int output=ps.executeUpdate();
			if(output==0) {
				throw new IncidentNumberNotFoundException();
			}
			return output > 0;
		} catch (IncidentNumberNotFoundException e) {
			System.out.println(e);
			return false;
		}
		catch (SQLException e) {
			throw new DatabaseException("Error updating incident status: " + e.getMessage());
		}
	}

	@Override
	public Collection<Incidents> getIncidentsInDateRange(Date startDate, Date endDate) throws DatabaseException {
		List<Incidents> incidents = new ArrayList<>();
		String sql = "SELECT * FROM Incidents WHERE IncidentDate BETWEEN ? AND ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setDate(1, new java.sql.Date(startDate.getTime()));
			ps.setDate(2, new java.sql.Date(endDate.getTime()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				incidents.add(new Incidents(rs.getInt("IncidentID"), rs.getString("IncidentType"),
						rs.getDate("IncidentDate"), rs.getString("Location"), rs.getString("Description"),
						rs.getString("Status"), rs.getInt("VictimID"), rs.getInt("SuspectID")));
			}
		} catch (SQLException e) {
			 throw new DatabaseException("Error fetching incidents: " + e.getMessage());
		}
		return incidents;
	}

	@Override
	public Collection<Incidents> searchIncidents(String criteria) throws DatabaseException {

		Collection<Incidents> incidents = new ArrayList<>();
		String sql = "SELECT * FROM Incidents WHERE IncidentType LIKE ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, "%" + criteria + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				incidents.add(new Incidents(rs.getInt("IncidentID"), rs.getString("IncidentType"),
						rs.getDate("IncidentDate"), rs.getString("Location"), rs.getString("Description"),
						rs.getString("Status"), rs.getInt("VictimID"), rs.getInt("SuspectID")));
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error searching incidents: " + e.getMessage());
		}
		return incidents;
	}

	@Override
	public Reports generateIncidentReport(Incidents incident) throws IncidentNumberNotFoundException, DatabaseException {

		String sql = "SELECT * FROM Reports WHERE incidentID = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, incident.getIncidentID());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int reportID = rs.getInt("reportid");
				int reportOfficer = rs.getInt("ReportingOfficer");
				Date reportDate = rs.getDate("reportDate");
				String ReportDetails = rs.getString("ReportDetails");
				String Status = rs.getString("status");

				return new Reports(reportID, incident.getIncidentID(), reportOfficer, reportDate, ReportDetails,
						Status);
			}
			else {
                throw new IncidentNumberNotFoundException();
            }
		} catch (SQLException e) {
			throw new DatabaseException("Error generating incident report: " + e.getMessage());
		}
	}

	@Override
	public Cases createCase(int caseid, String caseDescription, int incidentid) throws DatabaseException {
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
			throw new DatabaseException("Error creating case: " + e.getMessage());
		}
		return null;
	}

	@Override
	public Cases getCaseDetails(int caseId) throws CaseNotFoundException,DatabaseException {
		String sql = "SELECT * FROM Cases WHERE CaseID = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, caseId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				String caseDescription = rs.getString("CaseDescription");
				int incidentID = rs.getInt("incidentID");

				return new Cases(caseId, caseDescription, incidentID);
			}
			else {
                throw new CaseNotFoundException();
            }
		} catch (SQLException e) {
			throw new DatabaseException("Error fetching case details: " + e.getMessage());
		}
	}

	@Override
	public boolean updateCaseDetails(Cases caseDetails) throws DatabaseException {
		String sql = "UPDATE Cases SET CaseDescription = ?,incidentid= ? WHERE CaseID = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, caseDetails.getCaseDescription());
			ps.setInt(2, caseDetails.getIncidentID());
			ps.setInt(3, caseDetails.getCaseID());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DatabaseException("Error updating case details: " + e.getMessage());
		}
	}

	@Override
	public Collection<Cases> getAllCases() throws DatabaseException {
		Collection<Cases> cases = new ArrayList<>();
		String sql = "SELECT * FROM Cases";
		try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				int caseId = rs.getInt("CaseID");
				String caseDescription = rs.getString("CaseDescription");
				int incidentid = rs.getInt("incidentid");
				cases.add(new Cases(caseId, caseDescription, incidentid));
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error fetching all cases: " + e.getMessage());
		}
		return cases;
	}

	@Override
	public void closeConnection() throws SQLException {
		try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
        	throw new SQLException("Error closing connection: " + e.getMessage(), e);
        }
	}

}
