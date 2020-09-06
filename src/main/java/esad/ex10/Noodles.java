package esad.ex10;

/**
 * @author ashan on 2020-09-06
 */
public class Noodles extends Meal {

    public Noodles() {
        description = "Noodles";
    }

    @Override
    public double cost() {
        return 90;
    }
}
