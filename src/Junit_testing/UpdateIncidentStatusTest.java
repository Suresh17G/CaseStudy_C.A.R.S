package Junit_testing;

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

import dao.CrimeAnalysisServiceImpl;

class UpdateIncidentStatusTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @InjectMocks
    private CrimeAnalysisServiceImpl service; // The class under test

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    void testUpdateIncidentStatus() throws SQLException {
        // Arrange
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate successful update

        // Act
        boolean result = service.updateIncidentStatus("Closed", 1);

        // Assert
        assertTrue(result, "Incident status update failed!");

        // Verify interactions with the mock objects
        verify(mockPreparedStatement, times(1)).setString(1, "Closed");
        verify(mockPreparedStatement, times(1)).setInt(2, 1);
        
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testInvalidStatusUpdate() throws SQLException {
        // Arrange
        when(mockPreparedStatement.executeUpdate()).thenReturn(0); // Simulate no rows updated

        // Act
        boolean result = service.updateIncidentStatus("Closed", 999); // Non-existent ID

        // Assert
        assertFalse(result, "Status update should have failed for non-existent ID!");

        // Verify interactions with the mock objects
        verify(mockPreparedStatement, times(1)).setString(1, "Closed");
        verify(mockPreparedStatement, times(1)).setInt(2, 999);
        
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}
