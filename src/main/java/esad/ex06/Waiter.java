package esad.ex06;

/**
 * @author ashan on 2020-08-23
 */
public class Waiter {
    private Order juiceOrder;
    private Order mealOrder;
    private Order dessertOrder;

    void setOrder(Order juiceOrder, Order mealOrder, Order dessertOrder) {
        this.dessertOrder = dessertOrder;
        this.juiceOrder = juiceOrder;
        this.mealOrder = mealOrder;
    }

    void selectMeal() {
        mealOrder.execute();
    }

    void selectDessert() {
        dessertOrder.execute();
    }

    void selectJuice() {
        juiceOrder.execute();
    }
}
