import java.util.Scanner;

public class CGPACalculator {

    private static int convertMarksToGradePoint(double marks) {
        if (marks >= 90) return 10;
        else if (marks >= 80) return 9;
        else if (marks >= 70) return 8;
        else if (marks >= 60) return 7;
        else if (marks >= 50) return 6;
        else if (marks >= 40) return 5;
        else return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int SUBJECTS = 5;
        double[] marks = new double[SUBJECTS];
        double[] credits = new double[SUBJECTS];

        System.out.println("=== CGPA Calculator (Marks Based) ===");
        for (int i = 0; i < SUBJECTS; i++) {
            System.out.printf("Enter marks for Subject %d (out of 100): ", i + 1);
            marks[i] = scanner.nextDouble();

            System.out.printf("Enter credits for Subject %d: ", i + 1);
            credits[i] = scanner.nextDouble();
        }

        double totalPoints = 0;
        double totalCredits = 0;

        for (int i = 0; i < SUBJECTS; i++) {
            int gradePoint = convertMarksToGradePoint(marks[i]);
            totalPoints += gradePoint * credits[i];
            totalCredits += credits[i];
        }

        if (totalCredits == 0) {
            System.out.println("Invalid credits. Cannot calculate CGPA.");
        } else {
            double cgpa = totalPoints / totalCredits;
            System.out.printf("Your CGPA is: %.2f%n", cgpa);
        }

        scanner.close();
    }
}
