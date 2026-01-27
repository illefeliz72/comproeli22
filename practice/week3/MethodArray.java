
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
            double sum = sumColumn(matrix, col);
            System.out.println("Sum of the elements at column " + col + " is " + sum);
        }

        System.out.println("Sum of the elements in the major diagonal is "
                + sumMajorDiagonal(matrix));

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

    // method 2
    public static double sumMajorDiagonal(double[][] m) {
        double sum = 0;
        for (int i = 0; i < m.length; i++) {
            // The major diagonal always has the same row and column index
            sum += m[i][i];
        }
        return sum;
    }
}