package esad.ex10;

/**
 * @author ashan on 2020-09-06
 */
public class Bread extends Meal {
    public Bread() {
        description = "Bread";
    }

    @Override
    public double cost() {
        return 80;
    }
}
