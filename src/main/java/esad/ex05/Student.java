package esad.ex05;

/**
 * @author ashan on 2020-08-23
 */
public abstract class Student {
    IFestival festival;
    IPrograms programs;

    abstract void displayStudents();

    public void setFestival(IFestival festival) {
        this.festival = festival;
    }

    public void setPrograms(IPrograms programs) {
        this.programs = programs;
    }

    public void offerPrograms(){
        programs.offerPrograms();
    }

    public void conductEvents(){
        festival.performEvent();
    }
}
