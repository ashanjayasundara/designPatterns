package da;


import utils.CsvReader;
import utils.CsvWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ashan on 2020-12-16
 */
public class ProductDataETLApp {
    public static void main(String[] args) throws Exception {
        String transactionFilePath = "C:\\Users\\ashan\\Downloads\\Data+Sets\\Data Sets\\Online Retail Data\\Online Retail.csv";
        String productDataFilePath = "C:\\Users\\ashan\\Downloads\\Data+Sets\\Data Sets\\Online Retail Data\\product.csv";
        String dimProductDataFilePath = "C:\\Users\\ashan\\Downloads\\Data+Sets\\Data Sets\\Online Retail Data\\DimProduct.csv";

        Map<String, InvoiceProductInfo> invoiceProductInfoMap = new HashMap<>();
        Map<String, ProductDetails> productDetailsMap = new HashMap<>();
        Map<String, DimProductInfo> DimProductInfoMap = new HashMap<>();

        try (CsvReader reader = new CsvReader(transactionFilePath)) {
            reader.readHeaders();
            reader.stream().forEach(csv -> {
                InvoiceProductInfo productInfo = new InvoiceProductInfo(csv.getString("StockCode"), csv.getString("Description"), csv.getDouble("UnitPrice"));
                invoiceProductInfoMap.put(productInfo.getStockCode(), productInfo);
            });
        }

        System.out.println("Total Unique Items Count : " + invoiceProductInfoMap.size());

        try (CsvReader reader = new CsvReader(productDataFilePath)) {
            reader.readHeaders();
            reader.stream().forEach(csv -> {
                ProductDetails productDetails = new ProductDetails();
                productDetails.setPid(csv.getString("Uniq Id"))
                        .setProductName(csv.getString("Product Name"))
                        .setProductDesc(csv.getString("Description"))
                        .setBrand(csv.getString("Brand"))
                        .setItemNo(csv.getString("Item Number"))
                        .setPackageSize(csv.getString("Package Size"))
                        .setCategory(csv.getString("Category"))
                        .setAvailability(csv.getString("Available"));
                productDetailsMap.put(productDetails.getPid(), productDetails);
            });

        }

        int position = 0;
        List<ProductDetails> productDetailsList = new ArrayList<>(productDetailsMap.values());

        for (InvoiceProductInfo invoiceProductInfo : invoiceProductInfoMap.values()) {
            DimProductInfo dimProductInfo = new DimProductInfo(invoiceProductInfo, productDetailsList.get(position));
            DimProductInfoMap.put(dimProductInfo.getStockCode(), dimProductInfo);
            position++;

        }

        try (CsvWriter writer = new CsvWriter(dimProductDataFilePath)) {
            writer.setHeader(new String[]{"StockCode", "Product Name", "Description", "Brand", "Item Number", "Package Size", "Category", "UnitPrice", "Available"});
            DimProductInfoMap.values().forEach(dimProductInfo -> {
                writer.write(dimProductInfo.writeLine());
            });
        }

        System.out.println("Product Info Transformation Completed");
    }

    static class InvoiceProductInfo {
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

    static class ProductDetails {
        private String pid;
        private String productName;
        private String productDesc;
        private String brand;
        private String itemNo;
        private String packageSize;
        private String category;
        private String availability;

        String getPid() {
            return pid;
        }

        String getProductName() {
            return productName;
        }

        String getProductDesc() {
            return productDesc;
        }

        String getBrand() {
            return brand;
        }

        String getItemNo() {
            return itemNo;
        }

        String getPackageSize() {
            return packageSize;
        }

        String getCategory() {
            return category;
        }

        String getRootCategory() {
            return category.split("\\|")[0];
        }

        String getAvailability() {
            return availability;
        }

        ProductDetails setPid(String pid) {
            this.pid = pid;
            return this;
        }

        ProductDetails setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        ProductDetails setProductDesc(String productDesc) {
            this.productDesc = productDesc;
            return this;
        }

        ProductDetails setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        ProductDetails setItemNo(String itemNo) {
            this.itemNo = itemNo;
            return this;
        }

        ProductDetails setPackageSize(String packageSize) {
            this.packageSize = packageSize;
            return this;
        }

        ProductDetails setCategory(String category) {
            this.category = category;
            return this;
        }

        ProductDetails setAvailability(String availability) {
            this.availability = availability;
            return this;
        }
    }

    static class DimProductInfo {
        private String stockCode;
        private String description;
        private double unitPrice;
        private String pid;
        private String productName;
        private String productDesc;
        private String brand;
        private String itemNo;
        private String packageSize;
        private String category;
        private String availability;

        DimProductInfo(InvoiceProductInfo invoiceProductInfo, ProductDetails productDetails) {
            stockCode = invoiceProductInfo.getStockCode();
            description = invoiceProductInfo.getDescription();
            unitPrice = invoiceProductInfo.getUnitPrice();

            pid = productDetails.getPid();
            productName = productDetails.getProductName();
            productDesc = productDetails.getProductDesc();
            brand = productDetails.getBrand();
            itemNo = productDetails.getItemNo();
            packageSize = productDetails.getPackageSize();
            category = productDetails.getRootCategory();
            availability = productDetails.getAvailability();

        }

        String getStockCode() {
            return stockCode.trim();
        }

        String getDescription() {
            return description.trim();
        }

        double getUnitPrice() {
            return unitPrice;
        }

        String getPid() {
            return pid.trim();
        }

        String getProductName() {
            return productName.trim();
        }

        String getProductDesc() {
            return productDesc.trim();
        }

        String getBrand() {
            return brand.trim();
        }

        String getItemNo() {
            return itemNo.trim();
        }

        String getPackageSize() {
            return packageSize.trim();
        }

        String getCategory() {
            return category.trim();
        }

        String getAvailability() {
            return availability.trim();
        }

        String[] writeLine() {
            return new String[]{
                    getStockCode(), getProductName(), getDescription(),
                    getBrand(), getItemNo(), getPackageSize(),
                    getCategory(), String.valueOf(getUnitPrice()), getAvailability()
            };
        }
    }
}
