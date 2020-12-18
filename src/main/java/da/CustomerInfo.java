package da;

import java.text.MessageFormat;

/**
 * @author ashan on 2020-12-18
 */
public class CustomerInfo {
    private String name;
    private String region;
    private String province;
    private String custType;
    private String custID;
    private int customerSK;
    private String activeDate = "2010-01-01";
    private String expiredDate = "";
    private String isCurrent = "TRUE";

    String excapeSpecialCharacters(String value) {
        return value.replaceAll("[^a-zA-Z0-9]", " ");
    }

    public String getActiveDate() {
        return excapeSpecialCharacters(activeDate.trim());
    }

    public String getExpiredDate() {
        return excapeSpecialCharacters(expiredDate.trim());
    }

    public String getIsCurrent() {
        return excapeSpecialCharacters(isCurrent.trim());
    }

    public int getCustomerSK() {
        return customerSK;
    }

    public void setCustomerSK(int customerSK) {
        this.customerSK = customerSK;
    }

    CustomerInfo(String name, String region, String province, String custType) {
        this.name = name;
        this.region = region;
        this.province = province;
        this.custType = custType;
    }

    CustomerInfo(int id, String custID, String name, String region, String province, String custType) {
        this.name = name;
        this.region = region;
        this.province = province;
        this.custType = custType;
        this.customerSK = id;
        this.custID = custID;
    }

    CustomerInfo(int id, String custID, String name, String region, String province, String custType, String activate, String expiredDate, String isCurrent) {
        this.name = name;
        this.region = region;
        this.province = province;
        this.custType = custType;
        this.customerSK = id;
        this.custID = custID;
        this.activeDate = activate;
        this.expiredDate = expiredDate;
        this.isCurrent = isCurrent;
    }

    CustomerInfo(CustomerInfo info) {
        this.name = info.getName();
        this.region = info.getRegion();
        this.province = info.getProvince();
        this.custType = info.getCustType();
    }

    String getName() {
        return excapeSpecialCharacters(name.trim());
    }

    String getRegion() {
        return excapeSpecialCharacters(region.trim());
    }

    String getProvince() {
        return excapeSpecialCharacters(province.trim());
    }

    String getCustType() {
        return excapeSpecialCharacters(custType.trim());
    }

    String getCustID() {
        return excapeSpecialCharacters(custID.trim());
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
        return new String[]{getCustomerSK() + "", getCustID(), getName(), getProvince(), getRegion(), getCustType(), getActiveDate(), getExpiredDate(), getIsCurrent()};
    }

    String writeSQL() {
        return MessageFormat.format("INSERT INTO Dim_Customer(CustomerSK,CustomerID,CustomerName,Province,Region,CustomerType,ActivatedDate,ExpiredDate,IsCurrent) VALUES ( {0},''{1}'',''{2}'',''{3}'',''{4}'',''{5}'',''{6}'',''{7}'',''{8}'');\n",
                getCustomerSK() + "", getCustID(), getName(), getProvince(), getRegion(), getCustType(), getActiveDate(), getExpiredDate(), getIsCurrent());
    }
}

