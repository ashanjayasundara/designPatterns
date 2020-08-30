package esad.ex08;

/**
 * @author ashan on 2020-08-30
 */
public class FailGradeHandler extends GradeHandler{
    @Override
    public void processResult(GradeRequest gradeRequest) {
        if (gradeRequest.getMarks() < 40) {
            System.out.println("Your Fail the module for marks = " + gradeRequest.getMarks());
        } else if (successor != null) {
            successor.processResult(gradeRequest);
        }
    }
}
