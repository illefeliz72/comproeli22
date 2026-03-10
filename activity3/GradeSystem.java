package activity3;

import java.util.Scanner;

public class GradeSystem {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("[1] Enter Grades");
            System.out.println("[2] Display Grades");
            System.out.println("[3] Exit");
            System.out.print("Choose option: ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    enterGradesMenu();
                    break;

                case 2:
                    displayGrades();
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void enterGradesMenu() {

        boolean back = false;

        while (!back) {
            System.out.println("\n--- ENTER GRADES MENU ---");
            System.out.println("[1] COMPRO2");
            System.out.println("[2] DSA");
            System.out.println("[3] OOP");
            System.out.println("[0] Go Back");
            System.out.print("Choose subject: ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("COMPRO2 grade saved...");
                    break;

                case 2:
                    System.out.println("DSA grade saved...");
                    break;

                case 3:
                    System.out.println("OOP grade saved...");
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayGrades() {
        System.out.println("\nDisplaying grades...");
        System.out.println("(Placeholder for actual grade data)");
    }
}