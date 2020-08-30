package esad.ex07;

/**
 * @author ashan on 2020-08-30
 */
public class Tea extends Beverages {
    @Override
    void brew() {
        System.out.println("Steeping the Tea.");
    }

    @Override
    void addCondiment() {
        System.out.println("Adding Lemon.");

    }
}
