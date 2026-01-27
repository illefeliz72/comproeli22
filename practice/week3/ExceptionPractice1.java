import java.util.Scanner;

public class ExceptionPractice1 {
    public static void main(String[] args) {
    
            int number = inputNumber();
            System.out.println("The number is " + number);
    }

    public static int inputNumber() {
        Scanner input = new Scanner(System.in);
        int num = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print("Enter a number: ");
                int number = input.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Invalid input or Error! Please try again.");
            }
        }
        return num;
    }
}