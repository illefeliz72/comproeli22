package activity4;

import java.util.Scanner;

public class App {
    static String[] subject = new String[10];
    static double[] prelim = new double[10];
    static double[] midterm = new double[10];
    static double[] finals = new double[10];
    static Scanner sc = new Scanner(System.in);
    static int count = 0;
    static int choice = 0;

    public static void main(String[] args) {

        try {
            System.out.println("""
                    \nMAIN MENU
                    [1] Add Grade for subject
                    [2] Exit
                    """);
           System.out.print("Enter Choice: "); 
           choice = sc.nextInt(); sc.nextLine();

        } catch (Exception e) {

        }

    }
}