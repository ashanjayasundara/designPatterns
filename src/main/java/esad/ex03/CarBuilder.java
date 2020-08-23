package esad.ex03;

/**
 * @author ashan on 2020-08-16
 */
public class CarBuilder extends VehicleBuilder {
    private Vehicle vehicle;

    public CarBuilder() {
        vehicle = new Car();
    }

    @Override
    public void assembleVehicle() {
        vehicle.setTyre("4");
        vehicle.setEngine("1500CC");
        vehicle.setChassis("CAR_CHASSIS");
        vehicle.setOuterFramework("SEDAN");
    }

    @Override
    public Vehicle getVehicle() {
        System.out.println("Returning the Car");
        return vehicle;
    }
}
