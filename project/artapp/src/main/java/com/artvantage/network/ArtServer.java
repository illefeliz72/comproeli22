package com.artvantage.network;

import com.artvantage.models.ArtProject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ArtServer {
    private static Map<String, ArtProject> gallery = new ConcurrentHashMap<>();
    private static final String DATABASE = "art_gallery.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("[SERVER] ArtVantage | Server Online");
        System.out.println("[SERVER] Port: 5000 | Status: Listening...");
        System.out.println("==============================================");

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("\n[NEW CONNECTION] Artist connected from: " + clientSocket.getInetAddress());

                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Server crashed: " + e.getMessage());
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

                System.out.println("[DATA RECEIVED] ID: " + id + " | Title: " + art.getTitle());
                System.out.println("[DATABASE] Successfully updated " + DATABASE);

                out.writeObject("SUCCESS: Saved to JSON");
            }
        } catch (Exception e) {
            System.out.println("[DISCONNECT] Artist left the session.");
        }
    }

    private static synchronized void saveData() {
        try (FileWriter writer = new FileWriter(DATABASE)) {
            gson.toJson(gallery, writer);
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to write to JSON file.");
        }
    }
}