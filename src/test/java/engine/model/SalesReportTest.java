package engine.model;
import engine.model.SalesReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SalesReportTest {
    private SalesReport report;
    private InventoryReport inventoryReport;

    @BeforeEach
    void setUp() {
        // Avant chaque test, on crée un rapport frais
        report = new SalesReport("RPT-1", "Q3 Sales");
        inventoryReport = new InventoryReport("RPT-2", "Stock");
    }
    @Test
    void getTitle() {
        assertEquals("Q3 Sales", report.getTitle());
    }
    @Test
    void getId() {
        assertEquals("RPT-1", report.getId());
    }
    @Test
    void getReportStatus() {
        assertEquals(ReportStatus.DRAFT, report.getReportStatus());
    }
    @Test
    void getRecordCount() {
        assertEquals(0, report.getRecordCount());
    }
    @Test
    void testTotalRevenueCalcul() {
        List<DataRecord> dataRecordTest = new ArrayList<>();

        DataRecord record1 = new DataRecord();
        DataRecord record2 = new DataRecord();

        record1.addValue("amount", 100.0);
        record2.addValue("amount", 50.0);

        dataRecordTest.add(record1);
        dataRecordTest.add(record2);

        // On donne le classeur au rapport
        report.setRecords(dataRecordTest);

        // On vérifie que le calcul est bon (100 + 50 = 150)
        assertEquals(150.0, report.getTotalRevenue(), 0.01);
    }
    @Test
    void testGenerateSummaryText() {
        List<DataRecord> salesList = new ArrayList<>();
        List<DataRecord> inventList = new ArrayList<>();

        DataRecord salesR = new DataRecord();
        DataRecord inventR = new DataRecord();

        salesR.addValue("amount", 50.0);
        inventR.addValue("quantity", 7);

        salesList.add(salesR);
        inventList.add(inventR);

        report.setRecords(salesList);
        inventoryReport.setRecords(inventList);

        // Test des Summary
        String texteVentes = report.generateSummary();
        String texteInventaire = inventoryReport.generateSummary();

        assertTrue(texteVentes.contains("total"));
        assertTrue(texteInventaire.contains("stock"));
    }
}
