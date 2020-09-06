package esad.ex10;

/**
 * @author ashan on 2020-09-06
 */
public class Egg extends MealDecorator {
    Meal meal;

    public Egg(Meal meal) {
        this.meal = meal;
    }

    @Override
    public String getDescription() {
        return meal.getDescription() + ", Egg";
    }

    @Override
    public double cost() {
        return 30 + meal.cost();
    }
}