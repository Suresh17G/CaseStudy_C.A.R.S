package dao;

import java.util.Collection;
import java.util.Date;

import entity.Cases;
import entity.Incidents;
import entity.Reports;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {

	@Override
	public boolean createIncident(Incidents incident) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateIncidentStatus(String status, int incidentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Incidents> getIncidentsInDateRange(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Incidents> searchIncidents(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reports generateIncidentReport(Incidents incident) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cases createCase(String caseDescription, Collection<Incidents> incidents) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cases getCaseDetails(int caseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCaseDetails(Cases caseDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Cases> getAllCases() {
		// TODO Auto-generated method stub
		return null;
	}

}
