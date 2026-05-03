package com.artvantage.network;

import com.artvantage.models.ArtWork;
import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.*;

public class ArtServer {
    private static ArrayList<ArtWork> gallery = new ArrayList<>();
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        System.out.println("Art Server is starting...");
        try (ServerSocket server = new ServerSocket(5000)) {
            while (true) {
                try (Socket s = server.accept();
                        ObjectInputStream in = new ObjectInputStream(s.getInputStream())) {

                    ArtWork art = (ArtWork) in.readObject();
                    gallery.add(art);

          
                    try (FileWriter writer = new FileWriter("gallery.json")) {
                        gson.toJson(gallery, writer);
                    }
                    System.out.println("Art Added: " + art.title);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}