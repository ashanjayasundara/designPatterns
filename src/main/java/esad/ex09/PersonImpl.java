package esad.ex09;

/**
 * @author ashan on 2020-09-06
 */
public class PersonImpl implements IPerson {
    @Override
    public String generateFullName() {
        return "Udara Samaratunge";
    }

    @Override
    public String generateFullAddress() {
        return "No:100, Kandy Road, Gampaha";
    }
}
