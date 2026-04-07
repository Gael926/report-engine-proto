package engine.model;

public class InventoryReport extends Report {
    public InventoryReport(String id, String title) {
        super(id, title);
    }

    int stock;

    @Override
    public String generateSummary() {
        int inStockCount = 0;

        for (int i=0; i < getRecords().size(); i++) {
            DataRecord record = getRecords().get(i);
           Object value = record.getValue("quantity");
            if (value instanceof Number && ((Number) value).doubleValue() > 0) {
                inStockCount++;
            }
        }

        return "Inventory Report '" + getTitle() + "' : " + getRecords().size() + " products, " + inStockCount + " in stock, " + (getRecords().size() - inStockCount) + " out of stock";
    };
}
