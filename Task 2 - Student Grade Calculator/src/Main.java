//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.Scanner;

class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of subjects from the user
        System.out.print("Enter the total number of subjects: ");
        int subjectCount = scanner.nextInt();

        // Store marks in a dynamic list instead of an array
        ArrayList<Integer> marksList = new ArrayList<>();
        collectMarks(scanner, subjectCount, marksList);

        // Compute total and average percentage
        int totalScore = calculateTotal(marksList);
        double avgPercentage = computePercentage(totalScore, subjectCount);

        // Determine grade based on average percentage
        char grade = assignGrade(avgPercentage);

        // Display the final report
        displayResult(totalScore, avgPercentage, grade);

        scanner.close();
    }

    // Method to collect marks for all subjects
    private static void collectMarks(Scanner scanner, int count, ArrayList<Integer> marks) {
        for (int i = 1; i <= count; i++) {
            System.out.print("Enter marks for subject " + i + ": ");
            marks.add(scanner.nextInt());
        }
    }

    // Method to calculate total marks
    private static int calculateTotal(ArrayList<Integer> marks) {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return sum;
    }

    // Method to compute the average percentage
    private static double computePercentage(int total, int subjects) {
        return (double) total / subjects;
    }

    // Method to assign a grade using switch-case
    private static char assignGrade(double percentage) {
        int roundedPercentage = (int) percentage / 10; // Convert to a range (0-10)
        switch (roundedPercentage) {
            case 10:
            case 9: return 'A';
            case 8: return 'B';
            case 7: return 'C';
            case 6: return 'D';
            default: return 'F';
        }
    }

    // Method to display final results
    private static void displayResult(int total, double percentage, char grade) {
        System.out.println("-----------------------------");
        System.out.println("Total Score: " + total);
        System.out.printf("Average Percentage: %.2f%%\n", percentage);
        System.out.println("Final Grade: " + grade);
    }
}
