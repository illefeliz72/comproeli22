package com.client;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Received: " + msg);
                out.println("Server: " + msg);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }
}