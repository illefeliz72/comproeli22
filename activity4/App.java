package activity4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String[] subjects = new String[50];
        double[][] grades = new double[50][3];
        int subjectCount = 0;

        Scanner input = new Scanner(System.in);
        boolean running = true;

        System.out.println("""
                    *** STUDENT GRADE PORTFOLIO SYSTEM ***
                """);
        while (running) {
            System.out.println("""
                    \nMain Menu:
                      1.) Enter Grades:
                      2.) Exit (Save & Close):
                    """);
            System.out.print("Enter Choice:");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    if (subjectCount < 50) {
                        System.out.print("Enter Subject Name: ");
                        subjects[subjectCount] = input.nextLine();

                        boolean validGrades = false;
                        while (!validGrades) {
                            try {
                                System.out.print("Enter Prelim Grades: ");
                                grades[subjectCount][0] = input.nextDouble();

                                System.out.print("Enter Midterm Grades: ");
                                grades[subjectCount][1] = input.nextDouble();

                                System.out.print("Enter Final Grades: ");
                                grades[subjectCount][2] = input.nextDouble();

                                input.nextLine();

                                validGrades = true;
                                subjectCount++;
                                System.out.println(" Subject added ");

                            } catch (Exception e) {
                                System.out.println("Invalid Input. Try again.");
                                input.nextLine();

                                System.out.println("""
                                            Please restart grade for:
                                        """ + subjects[subjectCount]);
                            }
                        }
                    } else {
                        System.out.println("Portfolio is full.");
                    }
                    break;
                case "2":
                    saveToCSV(subjects, grades, subjectCount);
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println(" *** Application Closed. *** ");
    }

    public static void saveToCSV(String[] subjects, double[][] grades, int subjectCount) {
        String fileName = "grades.csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            
        } catch (Exception e) {
           
        }
    }
}
