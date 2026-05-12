package com.artvantage.network;

import com.artvantage.models.ArtProject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ArtServer {
    private static Map<String, ArtProject> gallery = new ConcurrentHashMap<>();
    private static final String DATABASE = "art_gallery.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        System.out.println("[SERVER] ArtVantage Server Online...");
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                saveData();

                if (art.getImageData() != null) {
                    Files.write(Paths.get("server_received_" + id + ".jpg"), art.getImageData());
                }

                System.out.println("[SERVER] Received and Saved ID: " + id);
                out.writeObject("SUCCESS: Saved text and image!");
            }
        } catch (Exception e) {
            System.out.println("Client disconnected.");
        }
    }

    private static synchronized void saveData() {
        try (FileWriter writer = new FileWriter(DATABASE)) {
            gson.toJson(gallery, writer);
        } catch (IOException e) {
            System.out.println("Save failed.");
        }
    }
}