package engine.service;
import engine.exception.DataSourceException;
import engine.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReportServiceTest {

    @Test
    void testGenerateReport() throws DataSourceException {
        DataSource fakeData = mock(DataSource.class);
        ReportService reportService = new ReportService(fakeData);

        when(fakeData.isAvailable()).thenReturn(true);

        List<DataRecord> faussesDonnees = new ArrayList<>();
        when(fakeData.fetchData("SELECT ALL")).thenReturn(faussesDonnees);

        Report myReport = reportService.generateReport("Sales", "Bilan annuel", "SELECT ALL");

        // Tests unitaires sur le fake Report qu'on vient de créer
        assertNotNull(myReport, "Le rapport ne doit pas être nul");
        assertEquals("Bilan annuel", myReport.getTitle());
        assertEquals(ReportStatus.DRAFT, myReport.getReportStatus());
    }
}
