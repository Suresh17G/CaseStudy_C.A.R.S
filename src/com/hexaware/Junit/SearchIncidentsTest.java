package com.hexaware.Junit;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hexaware.dao.CrimeAnalysisServiceImpl;
import com.hexaware.exception.DatabaseException;

class SearchIncidentsTest {

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
    void testSearchIncidents() throws SQLException, DatabaseException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // Act
        service.searchIncidents("Theft");

        // Verify
        verify(mockPreparedStatement, times(1)).setString(1, "%Theft%");
        verify(mockPreparedStatement, times(1)).executeQuery();
    }
}
