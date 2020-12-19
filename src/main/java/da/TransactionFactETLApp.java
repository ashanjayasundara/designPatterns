package da;

import org.apache.commons.collections4.ListUtils;
import utils.CsvReader;
import utils.CsvWriter;
import utils.FileUtils;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static da.CustomerDataETLApp.ROOT_FOLDER_PATH;


/**
 * @author ashan on 2020-12-18
 */
public class TransactionFactETLApp {
    public void generate() throws Exception {
        String transactionFilePath = new StringBuilder().append(ROOT_FOLDER_PATH).append("Online Retail.csv").toString();
        String dimCustomerDataFilePath = ROOT_FOLDER_PATH + "CustomerDimension.csv";
        String dimProductDataFilePath = ROOT_FOLDER_PATH + "ProductDimension.csv";

        String factTransactionETLFilePath = ROOT_FOLDER_PATH + "FactTransaction/FactTransaction.csv";
        String factTransactionDataFilePath = ROOT_FOLDER_PATH + "FactTransaction/FactTransaction_{0}.sql";

        Map<String, CustomerInfo> customerInfoMap = new HashMap<>();
        Map<String, DimProductInfo> productInfoMap = new HashMap<>();


        List<RetailTransactionDetails> transactionDetailsList = new ArrayList<>();
        List<FactTransactionInfo> factTransactionInfoList = new ArrayList<>();

        try (CsvReader reader = new CsvReader(transactionFilePath)) {
            reader.readHeaders();
            reader.stream().forEach(csv -> {
                RetailTransactionDetails retailTransactionDetails = new RetailTransactionDetails(
                        csv.getString(0),
                        csv.getString("StockCode"),
                        csv.getString("Description"),
                        csv.getInt("Quantity"),
                        csv.getString("InvoiceDate"),
                        csv.getDouble("UnitPrice"),
                        csv.getString("CustomerID"),
                        csv.getString("Country")
                );
                transactionDetailsList.add(retailTransactionDetails);
            });
        }
        System.out.println("Online Retail Transaction Data has been loaded");
        try (CsvReader reader = new CsvReader(dimCustomerDataFilePath)) {
            reader.readHeaders();
            reader.stream().forEach(csv -> {
                CustomerInfo customerInfo = new CustomerInfo(
                        csv.getInt("CustomerSK"),
                        csv.getString("CustomerID"),
                        csv.getString("CustomerName"),
                        csv.getString("Province"),
                        csv.getString("Region"),
                        csv.getString("CustomerType"),
                        csv.getString("ActivatedDate"),
                        csv.getString("ExpiredDate"),
                        csv.getString("IsCurrent"));
                customerInfoMap.put(customerInfo.getCustID(), customerInfo);
            });
        }
        System.out.println("Customer Dimension Data has been loaded");

        AtomicInteger count = new AtomicInteger(1);
        try (CsvReader reader = new CsvReader(dimProductDataFilePath)) {
            reader.readHeaders();
            reader.stream().forEach(csv -> {
                DimProductInfo dimProductInfo = new DimProductInfo();
                count.getAndIncrement();
                dimProductInfo.setProductSK(csv.getInt("ProductSK")).setStockCode(csv.getString("StockCode")).setProductName("Product Name").setDescription(csv.getString("Description")).
                        setBrand(csv.getString("Brand")).setItemNo(csv.getString("Item Number")).setPackageSize(csv.getString("Package Size")).setCategory(csv.getString("Category")).
                        setUnitPrice(csv.getDouble("UnitPrice")).setDiscount(csv.getInt("Discount")).setAvailability(csv.getString("Available")).setActivatedDate(csv.getString("ActivatedDate")).
                        setExpiredDate(csv.getString("ExpiredDate")).setIsCurrent(csv.getString("IsCurrent"));

                productInfoMap.put(dimProductInfo.getStockCode(), dimProductInfo);
            });
        }
        System.out.println("Product Dimension Data has been loaded");

        int errorCount = 0;
        for (RetailTransactionDetails transactionDetails : transactionDetailsList) {
            try {
                FactTransactionInfo factTransactionInfo = new FactTransactionInfo(productInfoMap.get(transactionDetails.getStockCode()),
                        customerInfoMap.get(transactionDetails.getCustomerID()), transactionDetails);
                factTransactionInfoList.add(factTransactionInfo);
            } catch (Exception ez) {
                System.out.println(ez.getMessage() + " == " + transactionDetails.getStockCode());
                errorCount++;
            }
        }
        System.out.println("Transformation Failed Count " + errorCount);

        try (CsvWriter writer = new CsvWriter(factTransactionETLFilePath)) {
            writer.setHeader(new String[]{"InvoiceNo", "ProductSK", "CustomerSK", "DateSK", "Quantity", "UnitPrice", "Discount", "GrossAmount", "ItemDiscount", "NetAmount", "Cost", "Profit", "Country"});
            factTransactionInfoList.forEach(dimProductInfo -> {
                writer.write(dimProductInfo.writeLine());
            });
        }
        System.out.println("Fact Transaction CSV file generated");
        int partitionID = 1;
        List<List<FactTransactionInfo>> partitionedList = ListUtils.partition(factTransactionInfoList, 50000);
        for (List<FactTransactionInfo> subTransactionList : partitionedList) {
            String pathName = MessageFormat.format(factTransactionDataFilePath, partitionID);
            FileUtils.createFile(pathName);
            Path filePath = Paths.get(pathName);
            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
                for (FactTransactionInfo factTransactionInfo : subTransactionList) {
                    writer.write(factTransactionInfo.writeSQL());
                }
            }
            partitionID++;
        }

        System.out.println("Fact Transaction SQL file generated");

    }

    public static void main(String[] args) throws Exception {
        TransactionFactETLApp transactionFactETLApp = new TransactionFactETLApp();
        transactionFactETLApp.generate();
    }
}
