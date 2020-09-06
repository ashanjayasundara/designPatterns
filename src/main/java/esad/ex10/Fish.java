package esad.ex10;

/**
 * @author ashan on 2020-09-06
 */
public class Fish extends MealDecorator {
    Meal meal;

    public Fish(Meal meal) {
        this.meal = meal;
    }

    @Override
    public String getDescription() {
        return meal.getDescription() + ", Fish";
    }

    @Override
    public double cost() {
        return 40 + meal.cost();
    }
}
