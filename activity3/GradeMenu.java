package activity3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

class Grade {
    String subject;
    double prelim;
    double midterm;
    double finals;

    Grade(String subject, double prelim, double midterm, double finals) {
        this.subject = subject;
        this.prelim = prelim;
        this.midterm = midterm;
        this.finals = finals;
    }

    double getAverage() {
        return (prelim + midterm + finals) / 3;
    }
}

public class GradeMenu {

    public static void main(String[] args) {

        // readinggg
        Scanner sc = new Scanner(System.in);

        // data structur
        ArrayList<Grade> gradeList = new ArrayList<>();
        int choice = 0;

        do {
            try {
                System.out.println("""
                        \nMenu:
                        [1] Add Grade for subject
                        [2] Display grades
                        [3] Exit
                        """);
                System.out.print("Choice: ");

                choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1) {

                    System.out.print("Subject: ");
                    String subject = sc.nextLine();

                    System.out.print("Prelim: ");
                    double prelim = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Midterm: ");
                    double midterm = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Finals: ");
                    double finals = sc.nextDouble();
                    sc.nextLine();

                    Grade g = new Grade(subject, prelim, midterm, finals);
                    gradeList.add(g);

                    System.out.println("Grade added successfully.");

                } else if (choice == 2) {
                    if (gradeList.isEmpty()) {
                        System.out.println("No grades recorded.");
                    } else {
                        for (Grade g : gradeList) {
                            System.out.printf("""
                                        \nSubject: %s
                                        Prelim: %s
                                        Midterm: %s
                                        Finals: %s
                                        Average: %s
                                    """, g.subject, g.prelim, g.midterm, g.finals, g.getAverage());
                        }
                    }

                } else if (choice == 3) {
                    System.out.println("Exiting program...");
                } else {
                    System.out.println("Invalid choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter correct data type.");
                sc.nextLine();
            }

        } while (choice != 3);

        sc.close();
    }
}
