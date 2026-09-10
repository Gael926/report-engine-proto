package org.esaip.ir4.lereun.gael;

import engine.exception.DataSourceException;
import engine.export.CsvExporter;
import engine.model.DataRecord;
import engine.model.Report;
import engine.service.DataSource;
import engine.service.ReportService;

import java.util.ArrayList;
import java.util.List;

public class App {

    // Source bouchonnee, juste pour faire tourner la demo sans base de donnees
    static class InMemoryDataSource implements DataSource {

        @Override
        public boolean isAvailable() {
            return true;
        }

        @Override
        public List<DataRecord> fetchData(String query) {
            List<DataRecord> records = new ArrayList<>();
            records.add(sale("Pizza", 120.0));
            records.add(sale("Pasta", 80.5));
            records.add(sale("Salad", 45.0));
            return records;
        }

        private DataRecord sale(String product, double amount) {
            DataRecord record = new DataRecord();
            record.addValue("product", product);
            record.addValue("amount", amount);
            return record;
        }
    }

    public static void main(String[] args) throws DataSourceException {
        ReportService service = new ReportService(new InMemoryDataSource());

        Report report = service.generateReport("SALES", "Bilan annuel", "SELECT ALL");

        System.out.println(report.generateSummary());
        System.out.println();
        System.out.print(new CsvExporter().export(report));
    }
}