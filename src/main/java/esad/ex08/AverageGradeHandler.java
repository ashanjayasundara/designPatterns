package esad.ex08;

/**
 * @author ashan on 2020-08-30
 */
public class AverageGradeHandler extends GradeHandler {
    @Override
    public void processResult(GradeRequest gradeRequest) {
        if (gradeRequest.getMarks() > 39 && gradeRequest.getMarks() <= 60) {
            System.out.println("Your Grade is C for marks = " + gradeRequest.getMarks());
        } else if (successor != null) {
            successor.processResult(gradeRequest);
        }
    }
}
