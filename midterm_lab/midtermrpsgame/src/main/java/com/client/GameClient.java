package com.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

import com.models.Player;

public class GameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;
    private static final int TOTAL_ROUNDS = 10;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             Scanner scanner = new Scanner(System.in)) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            System.out.print("Enter your player name: ");
            Player player = new Player(scanner.nextLine());
            out.writeObject(player);
            out.flush();

            System.out.println(in.readUTF()); 
            System.out.println(in.readUTF()); 

            for (int i = 0; i < TOTAL_ROUNDS; i++) {
                System.out.print(in.readUTF()); 
                
                int move = -1;
                while (move < 0 || move > 2) {
                    if (scanner.hasNextInt()) {
                        move = scanner.nextInt();
                        if (move < 0 || move > 2) System.out.print("Invalid. Enter 0, 1, or 2: ");
                    } else {
                        System.out.print("Invalid input. Enter 0, 1, or 2: ");
                        scanner.next(); 
                    }
                }

                out.writeInt(move);
                out.flush();
                System.out.println(in.readUTF()); 
            }

            System.out.println(in.readUTF()); 

        } catch (ConnectException e) {
            System.err.println("Could not connect to the server. Make sure GameServer is running first.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

