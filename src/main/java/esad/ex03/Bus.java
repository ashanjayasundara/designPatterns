package esad.ex03;

/**
 * @author ashan on 2020-08-16
 */
public class Bus extends Vehicle {

    @Override
    public void setTyre(String tyre) {
        System.out.println("Add Bus Tyres");
        super.tyre = tyre;
    }

    @Override
    public void setEngine(String engine) {
        System.out.println("Add Bus Engine");
        super.engine = engine;
    }

    @Override
    public void setOuterFramework(String outerFramework) {
        System.out.println("Fix the outer frame and finish the assemble work");
        super.outerFramework = outerFramework;
    }

    @Override
    public void setChassis(String chassis) {
        System.out.println("Fix the Chassy for the Bus");
        super.chassis = chassis;
    }
}
