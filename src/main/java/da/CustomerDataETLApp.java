package da;

import utils.CsvReader;
import utils.CsvWriter;

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
        String customerETLFilePath = ROOT_FOLDER_PATH + "CustomerDimension.csv";
        String customerETLSQLPath = ROOT_FOLDER_PATH + "CustomerDimension.sql";

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

        try (CsvWriter writer = new CsvWriter(customerETLFilePath)) {
            writer.setHeader(new String[]{"CustomerSK", "CustomerID", "CustomerName", "Province", "Region", "CustomerType", "ActivatedDate", "ExpiredDate", "IsCurrent"});
            for (CustomerInfo customerInfo : transfromedCustList) {
                writer.write(customerInfo.writeLine());
            }
        }
        Path filePath = Paths.get(customerETLSQLPath);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            for (CustomerInfo customerInfo : transfromedCustList) {
                writer.write(customerInfo.writeSQL());
            }
        }
        System.out.println("Customer Info Transformation Completed");
    }

    public static void main(String[] args) throws Exception {
        CustomerDataETLApp customerDataETLApp = new CustomerDataETLApp();
        customerDataETLApp.generateFile();


    }
//
//    static class CustomerInfo {
//        private String name;
//        private String region;
//        private String province;
//        private String custType;
//        private String custID;
//        private int customerSK;
//        private String activeDate = "2010-01-01";
//        private String expiredDate = "";
//        private String isCurrent = "TRUE";
//
//        public String getActiveDate() {
//            return activeDate;
//        }
//
//        public String getExpiredDate() {
//            return expiredDate;
//        }
//
//        public String getIsCurrent() {
//            return isCurrent;
//        }
//
//        public int getCustomerSK() {
//            return customerSK;
//        }
//
//        public void setCustomerSK(int customerSK) {
//            this.customerSK = customerSK;
//        }
//
//        CustomerInfo(String name, String region, String province, String custType) {
//            this.name = name;
//            this.region = region;
//            this.province = province;
//            this.custType = custType;
//        }
//
//        CustomerInfo(CustomerInfo info) {
//            this.name = info.getName();
//            this.region = info.getRegion();
//            this.province = info.getProvince();
//            this.custType = info.getCustType();
//        }
//
//        String getName() {
//            return name;
//        }
//
//        String getRegion() {
//            return region;
//        }
//
//        String getProvince() {
//            return province;
//        }
//
//        String getCustType() {
//            return custType;
//        }
//
//        String getCustID() {
//            return custID;
//        }
//
//        CustomerInfo setName(String name) {
//            this.name = name;
//            return this;
//        }
//
//        CustomerInfo setCustID(String custID) {
//            this.custID = custID;
//            return this;
//        }
//
//        String[] writeLine() {
//            return new String[]{getCustomerSK() + "", getCustID(), getName(), getProvince(), getRegion(), getCustType(), getActiveDate(), getExpiredDate(), getIsCurrent()};
//        }
//
//        String writeSQL() {
//            return MessageFormat.format("INSERT INTO Dim_Customer(CustomerSK,CustomerID,CustomerName,Province,Region,CustomerType,ActivatedDate,ExpiredDate,IsCurrent) VALUES ({0},''{1}'',''{2}'',''{3}'',''{4}'',''{5}'',''{6}'',''{7}'',''{8}'',);\n",
//                    getCustomerSK(), getCustID(), getName(), getProvince(), getRegion(), getCustType(), getActiveDate(), getExpiredDate(), getIsCurrent());
//        }
//    }
}
