package esad.ex05;

/**
 * @author ashan on 2020-08-23
 */
public class UnderGraduateStudents extends Student {

    UnderGraduateStudents() {
        setPrograms(new BScPrograms());
        setFestival(new CodeFest());
    }

    @Override
    void displayStudents() {
        System.out.println("Display under graduate student");
    }
}
