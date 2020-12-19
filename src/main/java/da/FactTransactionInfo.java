package da;

import java.text.MessageFormat;

/**
 * @author ashan on 2020-12-18
 */
public class FactTransactionInfo {
    private DimProductInfo productInfo;
    private CustomerInfo customerInfo;
    private RetailTransactionDetails transactionDetails;
    private double grossAmount;
    private double itemDiscount;
    private double discount;
    private double netAmount;
    private double cost;
    private double profit;

    public DimProductInfo getProductInfo() {
        return productInfo;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public RetailTransactionDetails getTransactionDetails() {
        return transactionDetails;
    }

    public double getGrossAmount() {
        return grossAmount;
    }

    public double getItemDiscount() {
        return itemDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public double getCost() {
        return cost;
    }

    public double getProfit() {
        return profit;
    }

    public FactTransactionInfo(DimProductInfo productInfo, CustomerInfo customerInfo, RetailTransactionDetails retailTransactionDetails) {
        this.productInfo = productInfo;
        this.customerInfo = customerInfo;
        this.transactionDetails = retailTransactionDetails;
        this.grossAmount = retailTransactionDetails.getQty() * productInfo.getUnitPrice();
        this.itemDiscount = (this.grossAmount * productInfo.getDiscount() / 100);
        this.netAmount = this.grossAmount * (100 - productInfo.getDiscount()) / 100;
        this.cost = this.grossAmount * 0.8;
        this.profit = this.netAmount - this.cost;
    }

    String[] writeLine() {
        return new String[]{transactionDetails.getInvoiceNo(), productInfo.getProductSK() + "", customerInfo.getCustomerSK() + "", transactionDetails.getDateSK() + "",
                transactionDetails.getQty() + "", productInfo.getDiscount() + "", getGrossAmount() + "", getItemDiscount() + "", getNetAmount() + "" + getCost() + "", getProfit() + "", transactionDetails.getCountry()};
    }

    String writeSQL() {
        return MessageFormat.format("INSERT INTO Fact_OnlineRetailTransactionInfo(InvoiceNo,ProductSK,CustomerSK,DateSK,Quantity,UnitPrice,Discount,GrossAmount,ItemDiscount,NetAmount,Cost,Profit) VALUES " +
                        "(''{0}'',{1},{2},{3},{4},{5},{6},{7},{8},{9},{10},{11});\n",
                transactionDetails.getInvoiceNo(), productInfo.getProductSK() + "", customerInfo.getCustomerSK() + "", transactionDetails.getDateSK() + "", transactionDetails.getQty() + "", productInfo.getUnitPrice() + "", productInfo.getDiscount() + "", getGrossAmount() + "", getItemDiscount() + "",
                getNetAmount() + "", getCost() + "", getProfit() + "");
    }
}
