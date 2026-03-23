package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ChatServer single-threaded implementation for 2 Clients (Peer bridging).
 * Because we are not using multithreading, the server must act as a strict
 * turn-based bridge.
 * It connects Client 1, then Client 2.
 * Then it passes messages exactly like this: Client 1 -> Server -> Client 2 ->
 * Client 2 -> Server -> Client 1.
 */
public class ChatServer {

    private static final int PORT = 8080;

    public void start() {
        System.out.println("Starting 2-Client Relay Server on port " + PORT + "...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            // ============================================
            // STEP 1: Connect Client 1
            // ============================================
            System.out.println("Waiting for Client 1 to connect...");
            Socket client1Socket = serverSocket.accept();
            PrintWriter out1 = new PrintWriter(client1Socket.getOutputStream(), true);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(client1Socket.getInputStream()));

            System.out.println("Client 1 has connected!");

            // Instruct Client 1 that they are the first player, and give them the token to
            // start.
            out1.println("1");

            // ============================================
            // STEP 2: Connect Client 2
            // ============================================
            System.out.println("Waiting for Client 2 to connect...");
            Socket client2Socket = serverSocket.accept();
            PrintWriter out2 = new PrintWriter(client2Socket.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(client2Socket.getInputStream()));

            System.out.println("Client 2 has connected!");

            // Instruct Client 2 that they are the second player, and must wait.
            out2.println("2");

            // ============================================
            // STEP 3: The Single-Threaded Relay Loop
            // ============================================
            System.out.println("Both clients connected. Starting relay...");

            while (true) {
                // TURN 1: Server pauses and waits to read from Client 1
                String msgFrom1 = in1.readLine();
                if (msgFrom1 == null || msgFrom1.equalsIgnoreCase("/quit")) {
                    System.out.println("Client 1 disconnected.");
                    out2.println("/quit"); // Inform Client 2 to shut down
                    break;
                }
                System.out.println("Relaying Client 1 -> Client 2");
                // Server sends that message instantly to Client 2
                out2.println(msgFrom1);

                // TURN 2: Server pauses and waits to read from Client 2
                String msgFrom2 = in2.readLine();
                if (msgFrom2 == null || msgFrom2.equalsIgnoreCase("/quit")) {
                    System.out.println("Client 2 disconnected.");
                    out1.println("/quit"); // Inform Client 1 to shut down
                    break;
                }
                System.out.println("Relaying Client 2 -> Client 1");
                // Server sends that message instantly to Client 1
                out1.println(msgFrom2);
            }

            // Clean up sockets once loop breaks
            client1Socket.close();
            client2Socket.close();

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    /*
     * 1-to-1 Server-to-Client Chat (Turn-based)
     * In this old version, the server itself acted as the second person typing
     * on the console.
     * public void start() {
     * System.out.println("Starting Single-Threaded Chat Server on port " + PORT +
     * "...");
     * 
     * // Try-with-resources to automatically close the ServerSocket when done
     * try (ServerSocket serverSocket = new ServerSocket(PORT)) {
     * System.out.println("Waiting for exactly ONE client to connect...");
     * 
     * // Step 1: Wait for a client to connect.
     * // accept() pauses the program until a connection is made.
     * try (Socket clientSocket = serverSocket.accept();
     * PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
     * BufferedReader in = new BufferedReader(new
     * InputStreamReader(clientSocket.getInputStream()));
     * Scanner consoleScanner = new Scanner(System.in)) {
     * 
     * System.out.println("Client connected! We are now in a turn-based chat.");
     * System.out.println("Rule: Client sends first, then Server sends back.");
     * 
     * String clientMessage;
     * // Step 2: Enter the Turn-Based Chat loop
     * while (true) {
     * System.out.println("\n[Waiting for client's turn...]");
     * 
     * // TURN 1: Server WAITS to read the client's message.
     * clientMessage = in.readLine();
     * 
     * if (clientMessage == null || clientMessage.equalsIgnoreCase("/quit")) {
     * System.out.println("Client disconnected or quit the chat.");
     * break;
     * }
     * 
     * System.out.println("Client says: " + clientMessage);
     * 
     * // TURN 2: Server's turn to type and send a message.
     * System.out.print("Your turn (Server): ");
     * String serverMessage = consoleScanner.nextLine();
     * 
     * // Send it over the network to the client
     * out.println(serverMessage);
     * 
     * if (serverMessage.equalsIgnoreCase("/quit")) {
     * System.out.println("You ended the chat.");
     * break;
     * }
     * }
     * 
     * }
     * } catch (IOException e) {
     * System.out.println("Server error: " + e.getMessage());
     * }
     * }
     */
}