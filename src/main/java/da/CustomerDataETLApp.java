package da;


import utils.CsvReader;
import utils.CsvWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ashan on 2020-12-16
 */
public class CustomerDataETLApp {
    public static void main(String[] args) throws Exception {

//        String transactionFilePath = "C:\\Users\\ashan\\Downloads\\Data+Sets\\Data Sets\\Online Retail Data\\CusID.csv";
        String transactionFilePath = "C:\\Users\\ashan\\Downloads\\Data+Sets\\Data Sets\\Online Retail Data\\Online Retail.csv";
        String customerInfoFilePath = "C:\\Users\\ashan\\Downloads\\Data+Sets\\Data Sets\\Online Retail Data\\cust_dimen.csv";
        String customerETLFilePath = "C:\\Users\\ashan\\Downloads\\Data+Sets\\Data Sets\\Online Retail Data\\CustETL.csv";

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
        int position = 0, iterationCount = 0;
        for (String custID : uniqueID) {
            if (position == customerInfoList.size()) {
                position = 0;
                iterationCount++;
            }
            CustomerInfo customerInfo = new CustomerInfo(customerInfoList.get(position));
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
            writer.setHeader(new String[]{"CustomerID", "CustomerName", "Province", "Region", "CustomerType"});
            transfromedCustList.forEach(customerInfo -> {
                writer.write(customerInfo.writeLine());
            });
        }
        System.out.println("Customer Info Transformation Completed");

    }

    static class CustomerInfo {
        private String name;
        private String region;
        private String province;
        private String custType;
        private String custID;

        CustomerInfo(String name, String region, String province, String custType) {
            this.name = name;
            this.region = region;
            this.province = province;
            this.custType = custType;
        }

        CustomerInfo(CustomerInfo info) {
            this.name = info.getName();
            this.region = info.getRegion();
            this.province = info.getProvince();
            this.custType = info.getCustType();
        }

        String getName() {
            return name;
        }

        String getRegion() {
            return region;
        }

        String getProvince() {
            return province;
        }

        String getCustType() {
            return custType;
        }

        String getCustID() {
            return custID;
        }

        CustomerInfo setName(String name) {
            this.name = name;
            return this;
        }

        CustomerInfo setCustID(String custID) {
            this.custID = custID;
            return this;
        }

        String[] writeLine() {
            return new String[]{getCustID(), getName(), getProvince(), getRegion(), getCustType()};
        }
    }
}
