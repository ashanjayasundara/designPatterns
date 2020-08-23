package esad.ex03;

/**
 * @author ashan on 2020-08-16
 */
public abstract class Vehicle {
    String chassis;
    String tyre;
    String engine;
    String outerFramework;

    public abstract void setTyre(String tyre);

    public abstract void setEngine(String engine);

    public abstract void setOuterFramework(String outerFramework);

    public abstract void setChassis(String chassis);

}
