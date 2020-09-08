package esad.ex10;

/**
 * @author ashan on 2020-09-06
 */
public class Chicken extends MealDecorator {
    Meal meal;

    public Chicken(Meal meal) {
        this.meal = meal;
    }

    @Override
    public String getDescription() {
        return meal.getDescription() + ", Chicken";
    }

    @Override
    public double cost() {
        return 50 + meal.cost();
    }
}
