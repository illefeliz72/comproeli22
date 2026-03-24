package com.hangmangame.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HangmanClient {

    public static void main(String[] args) {
         try (Socket socket = new Socket("localhost", 8000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter your name: ");
            out.println(sc.nextLine());

            String serverMsg;
            while ((serverMsg = in.readLine()) != null) {
                if (serverMsg.startsWith("WORD:")) {
                    System.out.println("Current word: " + serverMsg.substring(5));
                    System.out.print("Enter letter: ");
                    out.println(sc.nextLine());
                } else if (serverMsg.startsWith("RESULT:")) {
                    System.out.println(serverMsg.substring(7));
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Could not connect to server.");
        }
    }
}

    
