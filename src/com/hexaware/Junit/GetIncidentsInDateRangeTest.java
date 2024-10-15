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
import com.hexaware.exception.DatabaseException;

class GetIncidentsInDateRangeTest {

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
    void testGetIncidentsInDateRange() throws SQLException, DatabaseException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Mock one result
        when(mockResultSet.getInt("IncidentID")).thenReturn(1);

        // Act
        service.getIncidentsInDateRange(new Date(), new Date());

        // Verify that setDate is called twice (once for the start date, once for the end date)
        verify(mockPreparedStatement, times(2)).setDate(anyInt(), any());
        verify(mockPreparedStatement, times(1)).executeQuery();
    }
}
