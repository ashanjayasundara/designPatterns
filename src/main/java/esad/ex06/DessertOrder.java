package esad.ex06;

/**
 * @author ashan on 2020-08-23
 */
public class DessertOrder implements Order {
    Menu orderItem;

    public DessertOrder(Menu orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public void execute() {
        orderItem.prepareMenu();
    }
}
