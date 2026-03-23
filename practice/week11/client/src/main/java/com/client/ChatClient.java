package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * ChatClient single-threaded implementation for bridged connections.
 * This determines if it is Client 1 or Client 2 automatically based on the Server's instruction,
 * and maintains strict turn-based communication with the other client.
 */
public class ChatClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    public void start() {
        System.out.println("Attempting to connect to chat server at " + SERVER_ADDRESS + ":" + SERVER_PORT);

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner consoleScanner = new Scanner(System.in)) {

            System.out.println("Connected directly to the server!");

            // STEP 1: Read the configuration token from the Server to know who we are.
            // The first message tells us if we are "1" or "2"
            String turnToken = in.readLine();
            
            // Client 1 always sends first. Client 2 always waits first.
            boolean isMyTurn = turnToken.equals("1");

            if (isMyTurn) {
                System.out.println("--- You are Client 1. You start the chat! ---");
            } else {
                System.out.println("--- You are Client 2. Waiting for Client 1 to speak... ---");
            }

            // STEP 2: The strict turn-based loop
            while (true) {
                
                if (isMyTurn) {
                    // It's your turn. The program pauses for you to type in the console.
                    System.out.print("\nYour turn (You): ");
                    String myMessage = consoleScanner.nextLine();
                    
                    // Send it to the server (who relays it to the other client)
                    out.println(myMessage);
                    
                    if (myMessage.equalsIgnoreCase("/quit")) {
                        System.out.println("You ended the chat. Goodbye!");
                        break;
                    }

                    // After sending, your turn is over. You must now wait.
                    isMyTurn = false;
                    System.out.println("[Waiting for Peer's reply...]");

                } else {
                    // It's NOT your turn. The program pauses to wait for data coming over the network.
                    String peerMessage = in.readLine();
                    
                    if (peerMessage == null || peerMessage.equalsIgnoreCase("/quit")) {
                        System.out.println("The other client has disconnected or left the chat.");
                        break;
                    }

                    System.out.println("Peer: " + peerMessage);
                    
                    // The peer has spoken, so now it is your turn again.
                    isMyTurn = true;
                }
            }

        } catch (IOException e) {
            System.out.println("Network Error: Could not connect to the server. (Is it running?)");
        }
    }

/*
    // PREVIOUS IMPLEMENTATION: 1-to-1 Client-to-Server Chat (Turn-based)
    // In this old version, the client talked directly to the Server's console.
    public void start() {
        System.out.println("Attempting to connect to turn-based chat server at " + SERVER_ADDRESS + ":" + SERVER_PORT);

        // Step 1: Create a Socket to connect directly to the server.
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner consoleScanner = new Scanner(System.in)) {

            System.out.println("Connected to the server! Turn-based chat started.");
            System.out.println("Rule: You send first, then wait for the server's reply.");

            // Step 2: Enter the Turn-Based Chat loop
            while (true) {
                // TURN 1: Client's turn to type and send a message.
                System.out.print("\nYour turn (Client): ");
                String myMessage = consoleScanner.nextLine();
                
                // Send it over the network to the server
                out.println(myMessage);
                
                if (myMessage.equalsIgnoreCase("/quit")) {
                    System.out.println("You ended the chat.");
                    break;
                }

                System.out.println("[Waiting for server's reply...]");
                
                // TURN 2: Client WAITS to read the server's response.
                String serverMessage = in.readLine();
                
                if (serverMessage == null || serverMessage.equalsIgnoreCase("/quit")) {
                    System.out.println("Server disconnected or ended the chat.");
                    break;
                }

                System.out.println("Server says: " + serverMessage);
            }

        } catch (IOException e) {
            System.out.println("Network Error: Could not connect to the server. (Is it running?)");
        }
    }
*/
}