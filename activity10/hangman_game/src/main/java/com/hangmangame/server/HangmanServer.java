package com.hangmangame.server;

import java.io.*;
import java.net.*;

import com.hangmangame.service.HangmanService;

public class HangmanServer {

    private static String[] words = {
            "conference",
            "presidency",
            "accountant",
            "wilderness",
            "incredible",
            "attachment",
            "censorship",
            "opposition",
            "productive",
            "projection",
            "leadership",
            "inhabitant",
            "gregarious",
            "simplicity",
            "allocation",
            "curriculum",
            "corruption",
            "disability",
            "particular",
            "accessible",
            "conviction",
            "constraint",
            "competence",
            "litigation",
            "protection",
            "technology",
            "helicopter",
            "confidence",
            "chimpanzee",
            "remunerate",
            "photograph",
            "compliance",
            "confession",
            "attraction",
            "mainstream",
            "repetition" };
            
    private static String[] playerNames = new String[50];
    private static int[] playerScores = new int[50];
    private static int playerCount = 0;
    private static int port = 8000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Hangman Server is running on port");

            while (true) {
                try (Socket socket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    String name = in.readLine();

                    String randomWord = words[(int) (Math.random() * words.length)];
                    HangmanService game = new HangmanService(randomWord);

                    while (!game.isGameOver()) {
                        out.println("WORD:" + game.getHiddenWord());
                        String guessLine = in.readLine();
                        if (guessLine == null)
                            break;

                        game.processGuess(guessLine.toLowerCase().charAt(0));
                    }

                    playerNames[playerCount] = name;
                    playerScores[playerCount] = game.getScore();
                    playerCount++;

                    String result = game.isWin() ? "Winner!" : "Game Over! Word was: " + randomWord;
                    out.println("RESULT:" + result + " Score: " + game.getScore());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
