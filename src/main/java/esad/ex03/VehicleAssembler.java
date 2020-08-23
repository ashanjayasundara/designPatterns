package esad.ex03;

/**
 * @author ashan on 2020-08-16
 */
public class VehicleAssembler {
    private VehicleBuilder vehicleBuilder;

    public VehicleAssembler(VehicleBuilder vehicleBuilder) {
        this.vehicleBuilder = vehicleBuilder;
    }

    public void assembleVehicle() {
        System.out.println("Start assemble the vehicle");
        vehicleBuilder.assembleVehicle();
    }

    public Vehicle getVehicle() {
        return vehicleBuilder.getVehicle();
    }

}
