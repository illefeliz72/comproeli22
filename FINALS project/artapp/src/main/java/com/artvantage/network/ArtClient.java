package com.artvantage.network;

import com.artvantage.models.Illustration;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

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

        byte[] imageData = null;

        while (imageData == null) {
            System.out.print("Enter Image Path (REQUIRED): ");
            String path = sc.nextLine();
            
            if (path.isEmpty()) {
                System.out.println("[ERROR] Image path cannot be empty!");
                continue;
            }

            File file = new File(path);
            if (file.exists() && file.isFile()) {
                try {
                    imageData = new byte[(int) file.length()];
                    FileInputStream fis = new FileInputStream(file);
                    fis.read(imageData);
                    fis.close();
                    System.out.println("[SUCCESS] Image loaded.");
                } catch (Exception e) {
                    System.out.println("[ERROR] Could not read file. Try again.");
                    imageData = null;
                }
            } else {
                System.out.println("[ERROR] File not found at: " + path);
            }
        }

        try (Socket socket = new Socket("localhost", 5000);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Illustration art = new Illustration(title, price);
            art.setImageData(imageData);

            out.writeObject("ADD");
            out.writeObject(id);
            out.writeObject(art);

            String response = (String) in.readObject();
            System.out.println("Server Response: " + response);

        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        } finally {
            System.out.println("\nDAILY QUOTE: " + WDM.getDailyInspiration());
            System.out.println("--------------------------------------------");
            sc.close();
        }
    }
}