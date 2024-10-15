package com.hexaware.Junit;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hexaware.dao.CrimeAnalysisServiceImpl;
import com.hexaware.entity.Cases;
import com.hexaware.exception.DatabaseException;

class CreateCaseTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @InjectMocks
    private CrimeAnalysisServiceImpl service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    void testCreateCaseSuccess() throws SQLException, DatabaseException {
        // Arrange
        Cases newCase = new Cases(1, "Robbery", 101); // caseid, caseDescription, incidentid
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate successful case creation

        // Act
        Cases result = service.createCase(1, "Robbery", 101);

        // Assert
        assertNotNull(result, "Case creation returned null!");
        assertEquals(1, result.getCaseID());
        assertEquals("Robbery", result.getCaseDescription());
        assertEquals(101, result.getIncidentID());

        // Verify
        verify(mockPreparedStatement, times(1)).setInt(1, 1); // Verify caseid parameter
        verify(mockPreparedStatement, times(1)).setString(2, "Robbery"); // Verify caseDescription parameter
        verify(mockPreparedStatement, times(1)).setInt(3, 101); // Verify incidentid parameter
        verify(mockPreparedStatement, times(1)).executeUpdate(); // Verify executeUpdate called
    }

    @Test
    void testCreateCaseFailure() throws SQLException, DatabaseException {
        // Arrange
        when(mockPreparedStatement.executeUpdate()).thenReturn(0); // Simulate failure (no rows affected)

        // Act
        Cases result = service.createCase(2, "Burglary", 102);

        // Assert
        assertNull(result, "Case creation should have failed!");

        // Verify
        verify(mockPreparedStatement, times(1)).setInt(1, 2);
        verify(mockPreparedStatement, times(1)).setString(2, "Burglary");
        verify(mockPreparedStatement, times(1)).setInt(3, 102);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testCreateCaseDatabaseException() throws SQLException {
        // Arrange
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Database error"));

        // Act & Assert
        DatabaseException exception = assertThrows(DatabaseException.class, () -> {
            service.createCase(3, "Fraud", 103);
        });

        assertEquals("Error creating case: Database error", exception.getMessage());

        // Verify
        verify(mockPreparedStatement, times(1)).setInt(1, 3);
        verify(mockPreparedStatement, times(1)).setString(2, "Fraud");
        verify(mockPreparedStatement, times(1)).setInt(3, 103);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}
