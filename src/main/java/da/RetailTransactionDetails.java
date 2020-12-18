package da;

/**
 * @author ashan on 2020-12-18
 */
public class RetailTransactionDetails {
    private String invoiceNo;
    private String stockCode;
    private String description;
    private int qty;
    private String invoiceDate;
    private double unitPrice;
    private String customerID;
    private String country;
    private int dateSK;

    public int getDateSK() {
        return dateSK;
    }

    public void setDateSK(int dateSK) {
        this.dateSK = dateSK;
    }

    public RetailTransactionDetails(String invoiceNo, String stockCode, String description, int qty, String invoiceDate, double unitPrice, String customerID, String country) {
        this.invoiceNo = invoiceNo;
        this.stockCode = stockCode;
        this.description = description;
        this.qty = qty;
        this.invoiceDate = invoiceDate;
        this.unitPrice = unitPrice;
        this.customerID = customerID;
        this.country = country;
        this.dateSK = Integer.parseInt(invoiceDate.substring(0, 10).replaceAll("-", ""));
    }

    public String getInvoiceNo() {
        return excapeSpecialCharacters(invoiceNo.trim());
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getStockCode() {
        return excapeSpecialCharacters(stockCode.trim());
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getDescription() {
        return excapeSpecialCharacters(description.trim());
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getInvoiceDate() {
        return excapeSpecialCharacters(invoiceDate.trim());
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCustomerID() {
        return excapeSpecialCharacters(customerID.trim());
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCountry() {
        return excapeSpecialCharacters(country.trim());
    }

    public void setCountry(String country) {
        this.country = country;
    }

    String excapeSpecialCharacters(String value) {
        return value.replaceAll("[^a-zA-Z0-9]", " ");
    }
}
