package esad.ex06;

/**
 * @author ashan on 2020-08-23
 */
public class JuiceOrder implements Order {
    Menu orderItem;

    public JuiceOrder(Menu orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public void execute() {
        orderItem.prepareMenu();
    }
}
