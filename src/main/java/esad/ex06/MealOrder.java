package esad.ex06;

/**
 * @author ashan on 2020-08-23
 */
public class MealOrder implements Order {
    Menu orderItem;

    public MealOrder(Menu orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public void execute() {
        orderItem.prepareMenu();
    }
}
