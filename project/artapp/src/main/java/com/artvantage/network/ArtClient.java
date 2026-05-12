package com.artvantage.network;

import com.artvantage.models.Illustration;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        sc.nextLine(); 

        System.out.print("Enter Image Path (e.g., photo.jpg): ");
        String imagePath = sc.nextLine();

        try (Socket socket = new Socket("localhost", 5000);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Illustration art = new Illustration(title, price);
            
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                art.setImageData(Files.readAllBytes(Paths.get(imagePath)));
            }

            out.writeObject("ADD");
            out.writeObject(id);
            out.writeObject(art);

            String response = (String) in.readObject();
            System.out.println("Server says: " + response);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("DAILY QUOTE: " + WebDataModule.getDailyInspiration());
            System.out.println("--------------------------------------------");
            sc.close();
        }
    }
}