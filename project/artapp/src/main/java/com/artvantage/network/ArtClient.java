package com.artvantage.network;

import com.artvantage.models.ArtProject;
import com.artvantage.models.Illustration;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ArtClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- ArtVantage Client ---");
        System.out.print("Enter Art ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        try (Socket socket = new Socket("localhost", 5000);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject("ADD");
            out.writeObject(id);
            out.writeObject(new Illustration(title, price));

            String response = (String) in.readObject();
            System.out.println("Server says: " + response);

        } catch (Exception e) {
            System.out.println("Could not connect to server.");
        } finally {
            sc.close();
        }
        System.out.println("DAILY QUOTE: " + WebDataModule.getDailyInspiration());
        System.out.println("--------------------------------------------");
    }
}