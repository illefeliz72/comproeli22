package activity4;

import java.util.Scanner;

public class App1 {
    static String[] subjects = {"COMPRO1", "COMPRO2", "OOP", "DSA", "MMW"};
    // Rows = Students (50), Columns = Subjects (5)
    static double[][] scores = new double[50][subjects.length];
    static String[] studentNames = new String[50];
    static int studentCount = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-populating a few names for the example
        studentNames[0] = "Student A";
        studentNames[1] = "Student B";
        studentCount = 2; 

        while (true) {
            System.out.println("MAIN MENU:");
            System.out.println("[1] Enter Grades");
            System.out.println("[2] Display Grades");
            System.out.println("[3] Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    gradeMenu();
                    break;
                case 2:
                    displayGrades();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void gradeMenu() {
        while (true) {
            System.out.println("\nEnter grade for:");
            for (int i = 0; i < subjects.length; i++) {
                System.out.println("[" + (i + 1) + "] " + subjects[i]);
            }
            System.out.println("[0] Go Back");
            System.out.print("Enter choice: ");
            int subChoice = sc.nextInt();

            if (subChoice == 0) break;
            if (subChoice > 0 && subChoice <= subjects.length) {
                int subjectIndex = subChoice - 1;
                inputGradesForSubject(subjectIndex);
            } else {
                System.out.println("Invalid subject.");
            }
        }
    }

    public static void inputGradesForSubject(int subIndex) {
        System.out.println("\n--- Enter grades for " + subjects[subIndex] + " ---");
        for (int i = 0; i < studentCount; i++) {
            System.out.print("Grade for " + studentNames[i] + ": ");
            scores[i][subIndex] = sc.nextDouble();
        }
        System.out.println("Grades saved...");
    }

    public static void displayGrades() {
        System.out.println("\n--- CURRENT GRADES ---");
        System.out.print("Name\t\t");
        for (String sub : subjects) {
            System.out.print(sub + "\t");
        }
        System.out.println();

        for (int i = 0; i < studentCount; i++) {
            System.out.print(studentNames[i] + "\t");
            for (int j = 0; j < subjects.length; j++) {
                System.out.print(scores[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}