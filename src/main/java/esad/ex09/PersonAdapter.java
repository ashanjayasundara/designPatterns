package esad.ex09;

/**
 * @author ashan on 2020-09-06
 */
public class PersonAdapter implements IPerson {
    public IRemotePerson remotePerson;

    public PersonAdapter(IRemotePerson remotePerson) {
        this.remotePerson = remotePerson;
    }

    @Override
    public String generateFullName() {
        return getIterativeOutput(remotePerson.getFirstName() + " " + remotePerson.getLastName(), 4);
    }

    @Override
    public String generateFullAddress() {
        return getIterativeOutput(remotePerson.getAddressLine1() + " " + remotePerson.getAddressLine2(), 4);

    }

    private String getIterativeOutput(String content, int iterateCount) {
        StringBuilder stringBuilder = new StringBuilder(content);
        for (int i = 1; i < iterateCount; i++) {
            stringBuilder.append("\n").append(content);
        }
        return stringBuilder.toString();
    }

    public final class Nums {

    }
}
