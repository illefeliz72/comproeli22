package week2;

public class TheaterSeating1D {
    public static void main(String[] args) {
        int[] theaterRow = new int[8];
        int availableSeats = 0;
        theaterRow[3] = 1;
        for (int i = 0; i < theaterRow.length; i++) {
            System.out.println("seat "+ i + " status: " + theaterRow[i]);
        }
        if (theaterRow[1]== 0 ) {
            availableSeats++;
        }
        System.out.println("available seats: " + availableSeats);
    }
}