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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ArtServer {
    private static Map<String, ArtProject> gallery = new ConcurrentHashMap<>();
    private static final String DATABASE = "art_gallery.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        System.out.println("[SERVER] The ArtVantage Server has Started...");
        
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
            if (command.equals("ADD")) {
                String id = (String) in.readObject();
                ArtProject art = (ArtProject) in.readObject();
                gallery.put(id, art);
                saveData();
                out.writeObject("SUCCESS: Saved to JSON");
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