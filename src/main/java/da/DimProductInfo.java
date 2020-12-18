package da;

import java.text.MessageFormat;
import java.util.Random;

/**
 * @author ashan on 2020-12-18
 */
public class DimProductInfo {
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
    private int discount;

    private String activatedDate = "2010-01-01";
    private String expiredDate = "";
    private String isCurrent = "TRUE";
    private int productSK;

    public String getActivatedDate() {
        return activatedDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public String getIsCurrent() {
        return isCurrent;
    }

    public int getDiscount() {
        return discount;
    }

    public DimProductInfo setDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    public DimProductInfo setStockCode(String stockCode) {
        this.stockCode = stockCode;
        return this;
    }

    public DimProductInfo setDescription(String description) {
        this.description = description;
        return this;
    }

    public DimProductInfo setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public DimProductInfo setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public DimProductInfo setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public DimProductInfo setProductDesc(String productDesc) {
        this.productDesc = productDesc;
        return this;
    }

    public DimProductInfo setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public DimProductInfo setItemNo(String itemNo) {
        this.itemNo = itemNo;
        return this;
    }

    public DimProductInfo setPackageSize(String packageSize) {
        this.packageSize = packageSize;
        return this;
    }

    public DimProductInfo setCategory(String category) {
        this.category = category;
        return this;
    }

    public DimProductInfo setAvailability(String availability) {
        this.availability = availability;
        return this;
    }

    public DimProductInfo setActivatedDate(String activatedDate) {
        this.activatedDate = activatedDate;
        return this;
    }

    public DimProductInfo setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
        return this;
    }

    public DimProductInfo setIsCurrent(String isCurrent) {
        this.isCurrent = isCurrent;
        return this;
    }

    public DimProductInfo setProductSK(int productSK) {
        this.productSK = productSK;
        return this;
    }

    DimProductInfo(InvoiceProductInfo invoiceProductInfo, ProductDetails productDetails, Random random, int primaryKey) {
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
        discount = random.nextInt(15);
        productSK = primaryKey;

    }

    public int getProductSK() {
        return productSK;
    }

    String getStockCode() {
        return excapeSpecialCharacters(stockCode.trim());
    }

    String getDescription() {
        return excapeSpecialCharacters(description.trim());
    }

    double getUnitPrice() {
        return unitPrice;
    }

    String getPid() {
        return excapeSpecialCharacters(pid.trim());
    }

    String getProductName() {
        return excapeSpecialCharacters(productName.trim());
    }

    String getProductDesc() {
        return excapeSpecialCharacters(productDesc.trim());
    }

    String getBrand() {
        return excapeSpecialCharacters(brand.trim());
    }

    String getItemNo() {
        return excapeSpecialCharacters(itemNo.trim());
    }

    String getPackageSize() {
        return excapeSpecialCharacters(packageSize.trim());
    }

    public DimProductInfo() {
    }

    String getCategory() {
        return excapeSpecialCharacters(category.trim());
    }

    String excapeSpecialCharacters(String value) {
        return value.replaceAll("[^a-zA-Z0-9]", " ");
    }

    String getAvailability() {
        return excapeSpecialCharacters(availability.trim());
    }

    String[] writeLine() {
        return new String[]{
                getProductSK() + "",
                getStockCode(), getProductName(), getDescription(),
                getBrand(), getItemNo(), getPackageSize(),
                getCategory(), String.valueOf(getUnitPrice()), String.valueOf(getDiscount()),
                getAvailability(), getActivatedDate(), getExpiredDate(), getIsCurrent()
        };
    }

    String writeSQL() {
        return MessageFormat.format("INSERT INTO Dim_Product(ProductSK, StockCode,ProductName,Description,Brand,ItemNumber,PackageSize,Category,UnitPrice,Discount,Available,ActivatedDate,ExpiredDate,IsCurrent) VALUES ({0},''{1}'',''{2}'',''{3}'',''{4}'',''{5}'',''{6}'',''{7}'',{8},{9},''{10}'',''{11}'',''{12}'',''{13}'');\n",
                getProductSK() + "", getStockCode(), getProductName(), getDescription(), getBrand(), getItemNo(), getPackageSize(), getCategory(), getUnitPrice() + "", getDiscount(), getAvailability(), getActivatedDate(), getExpiredDate(), getIsCurrent());
    }
}
