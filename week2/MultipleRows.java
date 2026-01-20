package week2;

public class MultipleRows {
    public static void main(String[] args) {
        int rows = 5;
        int seatsPerRow = 8;

        char[][] theater = new char[rows][seatsPerRow];

        for (int r = 0; r < rows; r++) {
            for (int s = 0; s < seatsPerRow; s++) {
                theater[r][s] = '-';
            }
        }
        int row = 2;
        int seat = 4;
        theater[row][seat] = 'x';

        System.out.println("Theater Seating (- = Available, x = Taken):");
        for(int r = 0; r < rows; r++){
            for(int s = 0; s < seatsPerRow; s++){
                System.out.print(theater[r][s]+ " ");
            }
            System.out.println();
        }
    }
}