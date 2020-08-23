package esad.ex03;

/**
 * @author ashan on 2020-08-16
 */
public class Car extends Vehicle {
    @Override
    public void setTyre(String tyre) {
        System.out.println("Add Car Tyres");
        super.tyre = tyre;
    }

    @Override
    public void setEngine(String engine) {
        System.out.println("Add Car Engine");
        super.engine = engine;
    }

    @Override
    public void setOuterFramework(String outerFramework) {
        System.out.println("Fix the outer frame and finish the assemble work");
        super.outerFramework = outerFramework;
    }

    @Override
    public void setChassis(String chassis) {
        System.out.println("Fix the Chassy for the Car");
        super.chassis = chassis;
    }
}
