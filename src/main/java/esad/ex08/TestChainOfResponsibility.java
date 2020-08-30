package esad.ex08;

import java.util.Scanner;

/**
 * @author ashan on 2020-08-30
 */
public class TestChainOfResponsibility {
    public static void main(String[] args) {
        BestGradeHandler bestGradeHandler = new BestGradeHandler();
        MeritGradeHandler meritGradeHandler = new MeritGradeHandler();
        AverageGradeHandler averageGradeHandler = new AverageGradeHandler();
        FailGradeHandler failGradeHandler = new FailGradeHandler();

        bestGradeHandler.setSuccessor(meritGradeHandler);
        meritGradeHandler.setSuccessor(averageGradeHandler);
        averageGradeHandler.setSuccessor(failGradeHandler);

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter marks for Grade = ");
            int marks =  scanner.nextInt();
            bestGradeHandler.processResult(new GradeRequest(marks));
        }
    }
}
