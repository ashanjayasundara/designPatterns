package esad.ex03;

/**
 * @author ashan on 2020-08-16
 */
public class BusBuilder extends VehicleBuilder {
   private Vehicle vehicle;

    public BusBuilder() {
        vehicle = new Bus();
    }

    @Override
    public void assembleVehicle() {
        vehicle.setTyre("6");
        vehicle.setEngine("6500CC");
        vehicle.setChassis("BUS_CHASSIS");
        vehicle.setOuterFramework("DOUBLE_DECKER");
    }

    @Override
    public Vehicle getVehicle() {
        System.out.println("Returning the Bus");
        return vehicle;
    }
}
