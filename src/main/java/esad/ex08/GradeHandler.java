package esad.ex08;

/**
 * @author ashan on 2020-08-30
 */
public abstract class GradeHandler {
    protected GradeHandler successor;

    public void setSuccessor(GradeHandler handler) {
        this.successor = handler;
    }

    abstract public void processResult(GradeRequest gradeRequest);

}
