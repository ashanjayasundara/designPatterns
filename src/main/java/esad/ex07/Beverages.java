package esad.ex07;

/**
 * @author ashan on 2020-08-30
 */
public abstract class Beverages {
    abstract void brew();

    abstract void addCondiment();

    void boilWater(){
        System.out.println("Boiling Water");
    }

    void pourInCup(){
        System.out.println("Pour into cup");
    }

    final void prepareRecipe(){
        boilWater();
        brew();
        addCondiment();
        pourInCup();
    }
}
