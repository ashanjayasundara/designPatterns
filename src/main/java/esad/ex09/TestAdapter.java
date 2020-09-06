package esad.ex09;

/**
 * @author ashan on 2020-09-06
 */
public class TestAdapter {
    public static void main(String[] args) {
       IPerson person = new PersonImpl();
       IRemotePerson remotePerson = new RemotePersonImpl();

        System.out.println("Person says");
        System.out.println(person.generateFullAddress());
        System.out.println(person.generateFullAddress());

        System.out.println("\n Remote Persona says");
        System.out.println( remotePerson.getFirstName());
        System.out.println( remotePerson.getLastName());
        System.out.println( remotePerson.getAddressLine1());
        System.out.println( remotePerson.getAddressLine2());

        System.out.println("\n Person Adapter says");
        PersonAdapter adapter = new PersonAdapter(remotePerson);
        testPerson(adapter);

    }

    static void testPerson(PersonAdapter adapter){
        System.out.println(adapter.generateFullName());
        System.out.println(adapter.generateFullAddress());
    }
}
