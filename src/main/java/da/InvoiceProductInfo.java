package da;

/**
 * @author ashan on 2020-12-18
 */
public class InvoiceProductInfo {
    private String stockCode;
    private String description;
    private double unitPrice;

    InvoiceProductInfo(String stockCode, String description, double unitPrice) {
        this.stockCode = stockCode;
        this.description = description;
        this.unitPrice = unitPrice;
    }

    String getStockCode() {
        return stockCode;
    }

    String getDescription() {
        return description;
    }

    double getUnitPrice() {
        return unitPrice;
    }
}
