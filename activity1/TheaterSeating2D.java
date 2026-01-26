package week2;

public class TheaterSeating2D {
    public static void main(String[] args) {
        int[][] theater = new int[5][8];
        // rows, column ((seat at row 2, column 5))
        theater[2][5] = 1;
        // Book the seat at row 0, column 0
        theater[0][0] = 1;

        System.out.println("Theater Seating Chart (0=Available, 1=Booked):");
        for (int row = 0; row < theater.length; row++) {
            for (int col = 0; col < theater.length; col++) {
                System.out.print(theater[row][col] + " ");
            }
            System.out.println();
        }
        int bookedCount = 0;
        for (int row = 0; row < theater.length; row++) {
            for (int col = 0; col < theater.length; col++) {
                if (theater[row][col] == 1) {
                    bookedCount++;
                }
            }
        }
          System.out.println("total booked seats:" + bookedCount);
    }
}