package com.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner sc = new Scanner(System.in)) {

            for (int i = 0; i < 10; i++) {
                System.out.print("Enter move (0:Rock, 1:Paper, 2:Scissors): ");
                out.writeUTF(sc.nextLine());
                System.out.println("Result: " + in.readUTF());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}