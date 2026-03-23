package com.server;

public class Main {
    public static void main(String[] args) {
        // Instantiate our new ChatServer and start the infinite listening loop.
        ChatServer server = new ChatServer();
        server.start();
    }
}