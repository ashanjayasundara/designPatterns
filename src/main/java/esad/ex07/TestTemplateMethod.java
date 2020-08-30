package esad.ex07;

/**
 * @author ashan on 2020-08-30
 */
public class TestTemplateMethod {
    public static void main(String[] args) {
        System.out.println("============Tea==========\n");
        Beverages tea = new Tea();
        tea.prepareRecipe();

        System.out.println("============Coffee==========\n");
        Beverages coffee = new Coffee();
        coffee.prepareRecipe();

    }
}
