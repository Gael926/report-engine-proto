package engine.service;

import engine.exception.DataSourceException;
import engine.factory.ReportFactory;
import engine.model.DataRecord;
import engine.model.Report;

import javax.xml.crypto.Data;
import java.util.List;

public class ReportService {
    private DataSource dataSource;

    public ReportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public Report generateReport(String type, String title, String query) throws DataSourceException {
        if (dataSource.isAvailable()) {

            List<DataRecord> datas = dataSource.fetchData(query);
            Report report = ReportFactory.create(type, title);
            report.setRecords(datas);

            return report;

        } else {
            throw new DataSourceException("DataSource is not available");
        }
    }
}
