package com.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.models.MatchResult;
import com.models.Player;
import com.services.GameService;

public class GameServer {
    private static final int PORT = 12345;
    private static final int TOTAL_ROUNDS = 10;
    private static final GameService gameService = new GameService();

    public static void main(String[] args) {
        System.out.println("Server running. Waiting for 2 remote players...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // Setup Player 1
            Socket socket1 = serverSocket.accept();
            ObjectOutputStream out1 = new ObjectOutputStream(socket1.getOutputStream());
            ObjectInputStream in1 = new ObjectInputStream(socket1.getInputStream());
            Player p1 = (Player) in1.readObject();
            System.out.println(p1.getName() + " connected.");
            out1.writeUTF("Waiting for Player 2..."); out1.flush();

            // Setup Player 2
            Socket socket2 = serverSocket.accept();
            ObjectOutputStream out2 = new ObjectOutputStream(socket2.getOutputStream());
            ObjectInputStream in2 = new ObjectInputStream(socket2.getInputStream());
            Player p2 = (Player) in2.readObject();
            System.out.println(p2.getName() + " connected.");
            out2.writeUTF("Joined as Player 2."); out2.flush(); 
            // Start Game
            out1.writeUTF("Player 2 connected! Game starting..."); out1.flush();
            out2.writeUTF("Game starting..."); out2.flush();

            // 10 Round Loop
            for (int round = 1; round <= TOTAL_ROUNDS; round++) {
                String prompt = "\n--- ROUND " + round + " ---\nEnter move (0: Rock, 1: Paper, 2: Scissors): ";
                out1.writeUTF(prompt); out1.flush();
                out2.writeUTF(prompt); out2.flush();

                int move1 = in1.readInt();
                int move2 = in2.readInt();

                String roundResult = gameService.compareMoves(p1, p2, move1, move2);
                String scoreBoard = "Score -> " + p1.getName() + ": " + p1.getScore() + " | " + p2.getName() + ": " + p2.getScore();
                
                out1.writeUTF("Result: " + roundResult + "\n" + scoreBoard); out1.flush();
                out2.writeUTF("Result: " + roundResult + "\n" + scoreBoard); out2.flush();
            }

            // Game Conclusion
            MatchResult finalResult = gameService.determineMatchWinner(p1, p2);
            gameService.saveMatch(finalResult);
            
            String finalMessage = "\n=== GAME OVER ===\n" + finalResult.getSummary() + 
                                  "\n\n=== LEADERBOARD ===\n" + gameService.getLeaderboard();
            
            out1.writeUTF(finalMessage); out1.flush();
            out2.writeUTF(finalMessage); out2.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}