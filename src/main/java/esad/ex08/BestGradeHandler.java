package esad.ex08;

/**
 * @author ashan on 2020-08-30
 */
public class BestGradeHandler extends GradeHandler {
    @Override
    public void processResult(GradeRequest gradeRequest) {
        if (gradeRequest.getMarks() > 79 && gradeRequest.getMarks() <= 100) {
            System.out.println("Your Grade is A for marks = " + gradeRequest.getMarks());
        } else if (successor != null) {
            successor.processResult(gradeRequest);
        }
    }
}
