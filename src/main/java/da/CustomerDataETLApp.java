package da;

import utils.CsvReader;
import utils.CsvWriter;
import utils.FileUtils;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ashan on 2020-12-16
 */
public class CustomerDataETLApp {
    public static final String ROOT_FOLDER_PATH = "C:\\Users\\ashan\\Downloads\\Data+Sets\\Data Sets\\Online Retail Data\\";

    public void generateFile() throws Exception {
        String transactionFilePath = ROOT_FOLDER_PATH + "Online Retail.csv";
        String customerInfoFilePath = ROOT_FOLDER_PATH + "cust_dimen.csv";
        String customerETLFilePath = ROOT_FOLDER_PATH + "CustomerDimension/CustomerDimension.csv";
        String customerETLSQLPath = ROOT_FOLDER_PATH + "CustomerDimension/CustomerDimension.sql";

        Set<String> uniqueID = new HashSet<>();
        List<CustomerInfo> customerInfoList = new ArrayList<>();

        try (CsvReader reader = new CsvReader(transactionFilePath)) {
            reader.readHeaders();
            reader.stream().forEach(csv -> uniqueID.add(csv.getString("CustomerID")));
        }

        System.out.println("Unique Customer IDs are loaded Size :: " + uniqueID.size());

        try (CsvReader reader = new CsvReader(customerInfoFilePath)) {
            reader.readHeaders();
            reader.stream().forEach(csv -> {
                customerInfoList.add(new CustomerInfo(csv.getString("Customer_Name"), csv.getString("Region"), csv.getString("Province"), csv.getString("Customer_Segment")));
            });
        }

        List<CustomerInfo> transfromedCustList = new ArrayList<>();
        int position = 0, iterationCount = 0, incrementKey = 1;
        for (String custID : uniqueID) {
            if (position == customerInfoList.size()) {
                position = 0;
                iterationCount++;
            }
            CustomerInfo customerInfo = new CustomerInfo(customerInfoList.get(position));
            customerInfo.setCustomerSK(incrementKey++);
            if (iterationCount != 0) {
                customerInfo.setName(customerInfo.getName() + "_" + iterationCount)
                        .setCustID(custID);
            } else {
                customerInfo.setCustID(custID);
            }
            transfromedCustList.add(customerInfo);
            position++;

        }

        FileUtils.createFile(customerETLFilePath);
        FileUtils.createFile(customerETLSQLPath);

        try (CsvWriter writer = new CsvWriter(customerETLFilePath)) {
            writer.setHeader(new String[]{"CustomerSK", "CustomerID", "CustomerName", "Province", "Region", "CustomerType", "ActivatedDate", "ExpiredDate", "IsCurrent"});
            for (CustomerInfo customerInfo : transfromedCustList) {
                writer.write(customerInfo.writeLine());
            }
        }
        Path filePath = Paths.get(customerETLSQLPath);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            writer.write("SET IDENTITY_INSERT Dim_Customer ON\n\n");
            for (CustomerInfo customerInfo : transfromedCustList) {
                writer.write(customerInfo.writeSQL());
            }
            writer.write("\nSET IDENTITY_INSERT Dim_Customer OFF\n\n");
        }
        System.out.println("Customer Info Transformation Completed");
    }

    public static void main(String[] args) throws Exception {
        CustomerDataETLApp customerDataETLApp = new CustomerDataETLApp();
        customerDataETLApp.generateFile();
    }

}
