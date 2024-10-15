package com.hexaware.Junit;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hexaware.dao.CrimeAnalysisServiceImpl;
import com.hexaware.entity.Incidents;
import com.hexaware.entity.Reports;
import com.hexaware.exception.DatabaseException;
import com.hexaware.exception.IncidentNumberNotFoundException;

class GenerateIncidentReportTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private CrimeAnalysisServiceImpl service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    void testGenerateIncidentReport() throws SQLException, DatabaseException, IncidentNumberNotFoundException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("reportid")).thenReturn(1);
        when(mockResultSet.getDate("reportDate")).thenReturn(new java.sql.Date(new Date().getTime()));

        Incidents incident = new Incidents(1, "Theft", new java.util.Date(), "Location1", "Description1", "Open", 1, 2);

        // Act
        Reports report = service.generateIncidentReport(incident);

        // Assert
        assertNotNull(report, "Report generation failed!");

        // Verify
        verify(mockPreparedStatement, times(1)).setInt(1, 1);
        verify(mockPreparedStatement, times(1)).executeQuery();
    }
}
