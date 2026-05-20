package com.artvantage.network;

import com.artvantage.models.ArtProject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ArtServer {
    private static Map<String, ArtProject> gallery = new ConcurrentHashMap<>();
    private static final String DATABASE = "art_gallery.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        System.out.println("[SERVER] ArtVantage Server is Online...");
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket socket) {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            String command = (String) in.readObject();
            if ("ADD".equals(command)) {
                String id = (String) in.readObject();
                ArtProject art = (ArtProject) in.readObject();

                gallery.put(id, art);
                saveToDatabase();

                if (art.getImageData() != null) {
                    FileOutputStream fos = new FileOutputStream("server_received_" + id + ".jpg");
                    fos.write(art.getImageData());
                    fos.close();
                    System.out.println("[SERVER] Image saved for ID: " + id);
                }

                out.writeObject("SUCCESS: Art and Image processed.");
            }
        } catch (Exception e) {
            System.out.println("Client connection closed.");
        }
    }

    private static synchronized void saveToDatabase() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE))) {
            
            java.util.Map<String, Object> cleanGallery = new java.util.HashMap<>();

            
            for (Map.Entry<String, ArtProject> entry : gallery.entrySet()) {
                java.util.Map<String, Object> artData = new java.util.HashMap<>();
                artData.put("title", entry.getValue().getTitle());
                artData.put("price", entry.getValue().getPrice());
                cleanGallery.put(entry.getKey(), artData);
            }

            gson.toJson(cleanGallery, writer);
        } catch (IOException e) {
            System.out.println("Error saving JSON database.");
        }
    }
}
