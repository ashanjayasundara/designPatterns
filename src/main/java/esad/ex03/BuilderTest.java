package esad.ex03;

/**
 * @author ashan on 2020-08-16
 */
public class BuilderTest {
    public static void main(String[] args) {
        VehicleBuilder carBuilder = new CarBuilder();
        VehicleAssembler assembler = new VehicleAssembler(carBuilder);
        assembler.assembleVehicle();
        assembler.getVehicle();

        System.out.println("================================================");

        VehicleBuilder busBuilder = new BusBuilder();
        VehicleAssembler assembler2 = new VehicleAssembler(busBuilder);
        assembler2.assembleVehicle();
        assembler2.getVehicle();
    }
}
