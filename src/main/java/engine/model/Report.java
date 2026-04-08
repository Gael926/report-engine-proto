package engine.model;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Report {
    private String id;
    private String title;
    private LocalDateTime createdAt;
    private ReportStatus reportStatus;
    private List<DataRecord> records;

    public Report(String id, String title) {
        this.id = id;
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.reportStatus = ReportStatus.DRAFT;
        this.records = new ArrayList<>();
    }
    public abstract String generateSummary();

    public int getRecordCount() {
        return records.size();
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public ReportStatus getReportStatus() {
        return reportStatus;
    }
    public List<DataRecord> getRecords() {
        return records;
    }

    public void setReportStatus(ReportStatus status) {
        this.reportStatus = status;
    }
    public void setRecords(List<DataRecord> records) {
        this.records = records;
    }
}