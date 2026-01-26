
import java.util.Scanner;

public class MethodArray {
    // main method
    public static void main(String[] args) {
        double[][] matrix = new double[3][4];

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a 3-by-4 matrix row by row:");

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                matrix[r][c] = input.nextDouble();
            }
        }
        for (int col = 0; col < matrix[0].length; col++) {
            double sum =sumColumn(matrix, col);
            System.out.println("Sum of the elements at column " + col + " is " + sum );
        }

        input.close();
    }

    // method 1
    public static double sumColumn(double[][] m, int columnIndex) {
        double sum = 0;

        for (int row = 0; row < m.length; row++) {
            sum += m[row][columnIndex];
        }
        return sum;
    }
}