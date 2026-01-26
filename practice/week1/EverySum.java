package week1;

public class EverySum {
    public static void main(String[] args) {

        for (int n = 1; n < 50; n++) {
            int sum = 0;

            for (int i = 1; i <= n; i++) {
                sum += i;
            }

            System.out.println("Sum of 1 to " + n + " is " + sum);
        }
    }
}
