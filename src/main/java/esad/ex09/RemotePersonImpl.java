package esad.ex09;

/**
 * @author ashan on 2020-09-06
 */
public class RemotePersonImpl implements IRemotePerson {
    @Override
    public String getFirstName() {
        return "Nimal";
    }

    @Override
    public String getLastName() {
        return "Senevirathne";
    }

    @Override
    public String getAddressLine1() {
        return "No:50, New Kandy Road";
    }

    @Override
    public String getAddressLine2() {
        return "Malabe";
    }
}
