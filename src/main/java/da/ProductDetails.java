package da;

/**
 * @author ashan on 2020-12-18
 */
public class ProductDetails {
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
