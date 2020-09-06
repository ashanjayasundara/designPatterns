package esad.ex10;

/**
 * @author ashan on 2020-09-06
 */
public class Vegetable extends MealDecorator {
    Meal meal;

    public Vegetable(Meal meal) {
        this.meal = meal;
    }

    @Override
    public String getDescription() {
        return meal.getDescription() + ", Vegetable";
    }

    @Override
    public double cost() {
        return 20 + meal.cost();
    }
}
