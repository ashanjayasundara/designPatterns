package esad.ex10;

/**
 * @author ashan on 2020-09-06
 */
public class Rice extends Meal {
    public Rice() {
        description = "Rice";
    }

    @Override
    public double cost() {
        return 100;
    }
}
