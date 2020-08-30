package esad.ex08;

/**
 * @author ashan on 2020-08-30
 */
public class MeritGradeHandler extends GradeHandler {
    @Override
    public void processResult(GradeRequest gradeRequest) {
        if (gradeRequest.getMarks() >= 60 && gradeRequest.getMarks() < 80) {
            System.out.println("Your Grade is B for marks = " + gradeRequest.getMarks());
        } else if (successor != null) {
            successor.processResult(gradeRequest);
        }
    }
}
