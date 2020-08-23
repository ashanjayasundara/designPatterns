package esad.ex05;

/**
 * @author ashan on 2020-08-23
 */
public class PostGraduateStudents extends Student {
    public PostGraduateStudents() {
        setPrograms(new DoctoralPrograms());
        setFestival(new RoboFest());
    }

    @Override
    void displayStudents() {
        System.out.println("Display Post graduate student");
    }
}
