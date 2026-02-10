package week4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Writer {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        
        try{
            System.out.print("Enter first name: ");
            sb.append("First Name: ");
            sb.append(sc.nextLine());
            sb.append("\n");

            System.out.print("Enter last name: ");
            sb.append("Last Name: ");
            sb.append(sc.nextLine());
            sb.append("\n");

            System.out.print("Enter age: ");
            sb.append("Age: ");
            sb.append(sc.nextInt());
            sb.append("\n");
            //clean \n
            sc.nextLine();

            System.out.print("Enter email: ");
            sb.append("Email: ");
            sb.append(sc.nextLine());
            sb.append("\n");

            System.out.print("Enter phone number: ");
            sb.append("Phone Number: ");
            sb.append(sc.nextLine());
            sb.append("\n");


        }catch(InputMismatchException e){
            System.out.println(e.getMessage());
        }finally{
            sc.close();
        }

        try(FileWriter fw = new FileWriter("info.txt")){
            fw.write(sb.toString());
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}