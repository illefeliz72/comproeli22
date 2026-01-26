package week2;

public class TwoDimensionalArray {
    public static void main(String[] args) {
String[] clothesTypes = {"Shirts","Pants"};
      /*   String[][] clothColors = new String[2][3];
        // 1st alternative if data is not yet known initially
        clothColors[0][0] = "red";
        clothColors[0][1] = "blue";
        clothColors[0][2] = "green";
        // second row
        clothColors[1][0] = "orange";
        clothColors[1][1] = "yellow";
        clothColors[1][2] = "violet";
*/
        //scond alternative if data is known initially

        String [][] clothColors = {
            {"red","blue","green"},
            {"orange","yellow","violet"}

        };

        for(int i = 0; i < clothColors.length; i++){
            System.out.println(clothesTypes[i]);
            for(int j = 0; j < clothColors[i].length; j++){
                System.out.printf("%-8s", clothColors[i][j] + " ");
            }  System.out.println();
        }
       
    }

}
