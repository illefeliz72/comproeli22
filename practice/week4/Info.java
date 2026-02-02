package week4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Info {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
       try(Scanner sc = new Scanner(System.in)){
        System.out.println("First Name: ");
        sb.append("First Name: ").append(sc.nextLine()).append("\n");
         System.out.println("Last Name: ");
        sb.append("Last Name: ").append(sc.nextLine()).append("\n");
         System.out.println("Age: ");
        sb.append("Age: ").append(sc.nextInt()).append("\n");
        sc.nextLine();
         System.out.println("Email: ");
        sb.append("Email: ").append(sc.nextLine()).append("\n");
        System.out.println("Phone Number: ");
        sb.append("Phone Number: ").append(sc.nextLine()).append("\n");
       }catch(InputMismatchException e){
        System.out.println("INVALID INPUT TRY AGAIN.");
       }
       //TRY W RESOURCE
            try (FileWriter fw = new FileWriter("yourinfo.txt")) {
                fw.write(sb.toString());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
}