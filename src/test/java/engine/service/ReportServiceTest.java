package engine.service;
import engine.exception.DataSourceException;
import engine.model.Report;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReportServiceTest {

    @Test
    void testGenerateReport() throws DataSourceException {
        DataSource fakeData = mock(DataSource.class);
        ReportService reportService = new ReportService(fakeData);

        when(fakeData.isAvailable()).thenReturn(true);
        when(fakeData.fetchData("SELECT ALL")).thenReturn(fakeData);

        Report myReport = reportService.generateReport("Sales", "Bilan annuel", "SELECT ALL");
    }
}
