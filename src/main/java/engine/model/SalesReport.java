package engine.model;

import java.util.List;

public class SalesReport extends Report {
    public SalesReport(String id, String title) {
        super(id, title);
    }

    @Override
    public String generateSummary() {
        return "Sales Report : '" + getTitle() + "' : [" + getRecordCount() + "] records, total : [" + getTotalRevenue() + "]";
    };

    public double getTotalRevenue() {
        double total = 0.0;
        for (int i=0; i < super.getRecords().size(); i++) {
            // On demande la valeur associée à la clé "amount"
            DataRecord monRecord = super.getRecords().get(i);
            Object valeurBrute = monRecord.getValue("amount");

            // On transforme cet "Objet" en "Nombre" pour pouvoir faire l'addition
            if (valeurBrute instanceof Number) {
                total += ((Number) valeurBrute).doubleValue();
            }
        }
        return total;
    }
}
