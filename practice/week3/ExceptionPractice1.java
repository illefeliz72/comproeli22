import java.util.Scanner;

public class ExceptionPractice1 {
    public static void main(String[] args) {
        System.out.println("ENTER A NUMBER: ");
        int number = inputNumber();
        System.out.println("The number is " + number);
    }

    // method inputNumber()
    public static int inputNumber() {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                int number = input.nextInt();
                return number;
                
            } catch (Exception e) { 
                input.nextLine();
                System.out.println("Invalid input or Error! Please try again.");
                System.out.println("ENTER A NUMBER:");
                
            }
        }
    }
}