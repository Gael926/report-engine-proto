package engine.factory;
import engine.exception.ReportException;
import engine.model.InventoryReport;
import engine.model.Report;
import engine.model.SalesReport;

public class ReportFactory {
    public ReportFactory() {

    }
    private static int counter = 0;

    public static Report create(String type, String title) {
        counter++;
        String rapport_id = "RPT-" + counter;
        if ("SALES".equalsIgnoreCase(type)) {
            return new SalesReport(rapport_id, title);
        } else if ("INVENTORY".equalsIgnoreCase(type)) {
            return new InventoryReport(rapport_id, title);
        } else {
            throw new ReportException("Type de rapport inconnu : " + type);
        }
    }
}
