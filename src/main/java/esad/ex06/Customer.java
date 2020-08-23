package esad.ex06;

/**
 * @author ashan on 2020-08-23
 */
public class Customer {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();

        Menu juice = new Juice("Orange juice");
        Menu meal = new Meal("Noodles");
        Menu desert = new Dessert("Ice-cream");

        Order juiceOrder = new JuiceOrder(juice);
        Order mealOrder = new MealOrder(meal);
        Order desertOrder = new DessertOrder(desert);

        waiter.setOrder(juiceOrder, mealOrder, desertOrder);
        waiter.selectJuice();
        waiter.selectMeal();
        waiter.selectDessert();
    }
}
