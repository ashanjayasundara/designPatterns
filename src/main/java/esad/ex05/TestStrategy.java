package esad.ex05;

/**
 * @author ashan on 2020-08-23
 */
public class TestStrategy {
    public static void main(String[] args) {
        Student poStudent = new PostGraduateStudents();
        poStudent.offerPrograms();
        poStudent.conductEvents();
        poStudent.displayStudents();

        System.out.println("\n=============Assign new Event================");
        poStudent.setFestival(new CodeFest());
        poStudent.conductEvents();

        System.out.println("\n=============================================");

        Student unStudent = new PostGraduateStudents();
        unStudent.offerPrograms();
        unStudent.conductEvents();
        unStudent.displayStudents();

        System.out.println("\n=============Assign new Program===============");
        unStudent.setPrograms(new MScPrograms());
        unStudent.offerPrograms();
    }
}
