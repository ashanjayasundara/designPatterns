package da;

/**
 * @author ashan on 2020-12-19
 */
public class MainApp {
    public static void main(String[] args) throws Exception {
        CustomerDataETLApp customerDataETLApp = new CustomerDataETLApp();
        customerDataETLApp.generateFile();

        ProductDataETLApp productDataETLApp = new ProductDataETLApp();
        productDataETLApp.generate();

        TransactionFactETLApp transactionFactETLApp = new TransactionFactETLApp();
        transactionFactETLApp.generate();

        System.out.println("ETL Process completed");
    }
}
