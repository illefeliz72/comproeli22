import java.util.Scanner;

public class Array1 {
    public static void main(String[] args) {

        int[] numbers = { 1, 3, 34, 43, 6, 7, 11, 19, 30, 4 };
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        // prompt
    Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number");
        int num = sc.nextInt();
        int index = -1;
        for (int i = 0; i < numbers.length; i++){
            if (numbers[i] == num) {
                index = i;
                break;
            }
        }
        System.out.print("index of " + num + "is" + index);
    }
}
