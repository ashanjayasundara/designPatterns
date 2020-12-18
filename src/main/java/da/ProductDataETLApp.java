package da;


import utils.CsvReader;
import utils.CsvWriter;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static da.CustomerDataETLApp.ROOT_FOLDER_PATH;


/**
 * @author ashan on 2020-12-16
 */
public class ProductDataETLApp {
    public void generate() throws Exception {
        String transactionFilePath = ROOT_FOLDER_PATH + "Online Retail.csv";
        String productDataFilePath = ROOT_FOLDER_PATH + "product.csv";
        String dimProductDataFilePath = ROOT_FOLDER_PATH + "ProductDimension.csv";
        String dimProductSQLFilePath = ROOT_FOLDER_PATH + "ProductDimension.sql";

        Map<String, InvoiceProductInfo> invoiceProductInfoMap = new HashMap<>();
        Map<String, ProductDetails> productDetailsMap = new HashMap<>();
        Map<String, DimProductInfo> DimProductInfoMap = new HashMap<>();

        try (CsvReader reader = new CsvReader(transactionFilePath)) {
            reader.readHeaders();
            reader.stream().forEach(csv -> {
                InvoiceProductInfo productInfo = new InvoiceProductInfo(csv.getString("StockCode"), csv.getString("Description", ""), csv.getDouble("UnitPrice", 5));
                invoiceProductInfoMap.put(productInfo.getStockCode(), productInfo);
            });
        }
        Random random = new Random();
        random.nextInt(15);

        System.out.println("Total Unique Items Count : " + invoiceProductInfoMap.size());

        try (CsvReader reader = new CsvReader(productDataFilePath)) {
            reader.readHeaders();
            reader.stream().forEach(csv -> {
                ProductDetails productDetails = new ProductDetails();
                productDetails.setPid(csv.getString("Uniq Id"))
                        .setProductName(csv.getString("Product Name", "UNDEFINED"))
                        .setProductDesc(csv.getString("Description", "UNDEFINED"))
                        .setBrand(csv.getString("Brand", "UNDEFINED"))
                        .setItemNo(csv.getString("Item Number", "-"))
                        .setPackageSize(csv.getString("Package Size", "STANDARD"))
                        .setCategory(csv.getString("Category", ""))
                        .setAvailability(csv.getString("Available", "FALSE"));
                productDetailsMap.put(productDetails.getPid(), productDetails);
            });

        }

        int position = 0, increment = 1;
        List<ProductDetails> productDetailsList = new ArrayList<>(productDetailsMap.values());

        for (InvoiceProductInfo invoiceProductInfo : invoiceProductInfoMap.values()) {
            DimProductInfo dimProductInfo = new DimProductInfo(invoiceProductInfo, productDetailsList.get(position), random, increment++);
            DimProductInfoMap.put(dimProductInfo.getStockCode(), dimProductInfo);
            position++;

        }

        try (CsvWriter writer = new CsvWriter(dimProductDataFilePath)) {
            writer.setHeader(new String[]{"ProductSK", "StockCode", "Product Name", "Description", "Brand", "Item Number", "Package Size", "Category", "UnitPrice", "Discount", "Available", "ActivatedDate", "ExpiredDate", "IsCurrent"});
            DimProductInfoMap.values().forEach(dimProductInfo -> {
                writer.write(dimProductInfo.writeLine());
            });
        }

        Path filePath = Paths.get(dimProductSQLFilePath);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            for (DimProductInfo dimProductInfo : DimProductInfoMap.values()) {
                writer.write(dimProductInfo.writeSQL());
            }
        }

        System.out.println("Product Info Transformation Completed");
    }

    public static void main(String[] args) throws Exception {
        ProductDataETLApp productDataETLApp = new ProductDataETLApp();
        productDataETLApp.generate();
    }

}
