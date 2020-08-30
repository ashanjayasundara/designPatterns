package esad.ex07;

/**
 * @author ashan on 2020-08-30
 */
public class Coffee extends Beverages {
    @Override
    void brew() {
        System.out.println("Stripping coffee through filter.");
    }

    @Override
    void addCondiment() {
        System.out.println("Add sugar and milk");
    }
}
