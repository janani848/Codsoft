//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

class Student {
    private int numSubjects;
    private int totalMarks;
    private double averagePercentage;
    private char grade;

    public void inputMarks() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of subjects: ");
        numSubjects = scanner.nextInt();

        totalMarks = 0;
        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + i + ": ");
            totalMarks += scanner.nextInt();
        }

        scanner.close();
    }

    public void calculateGrade() {
        averagePercentage = (double) totalMarks / numSubjects;
        grade = (averagePercentage >= 90) ? 'A' :
                (averagePercentage >= 80) ? 'B' :
                        (averagePercentage >= 70) ? 'C' :
                                (averagePercentage >= 60) ? 'D' : 'F';
    }

    public void displayResult() {
        System.out.println("----------------------------");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
    }
}

class GradeCalculator {
    public static void main(String[] args) {
        Student student = new Student();
        student.inputMarks();
        student.calculateGrade();
        student.displayResult();
    }
}
