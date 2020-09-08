package esad.ex10;

/**
 * @author ashan on 2020-09-06
 */
public abstract class Meal {
    String description;

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
