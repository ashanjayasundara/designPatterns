package esad.ex06;

/**
 * @author ashan on 2020-08-23
 */
public class Meal implements Menu {
    private String menuItem;

    public Meal(String menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public void prepareMenu() {
        System.out.println("Preparing " + menuItem + " for the customer");
    }
}
