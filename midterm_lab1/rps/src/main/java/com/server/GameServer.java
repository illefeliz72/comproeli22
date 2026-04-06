package com.server;

import com.models.Player;
import com.models.GameResult;
import java.io.*;
import java.net.*;
import java.util.HashMap;

public class GameServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5000)) {
            System.out.println("Server started... Waiting for players.");

            Socket s1 = server.accept();
            DataInputStream in1 = new DataInputStream(s1.getInputStream());
            DataOutputStream out1 = new DataOutputStream(s1.getOutputStream());

            Socket s2 = server.accept();
            DataInputStream in2 = new DataInputStream(s2.getInputStream());
            DataOutputStream out2 = new DataOutputStream(s2.getOutputStream());

            Player p1 = new Player("Player 1");
            Player p2 = new Player("Player 2");

            for (int i = 0; i < 10; i++) {
                int m1 = Integer.parseInt(in1.readUTF());
                int m2 = Integer.parseInt(in2.readUTF());

                if (m1 == m2) {
                    out1.writeUTF("Draw");
                    out2.writeUTF("Draw");
                } else if ((m1 == 0 && m2 == 2) || (m1 == 1 && m2 == 0) || (m1 == 2 && m2 == 1)) {
                    p1.addWin();
                    out1.writeUTF("Win");
                    out2.writeUTF("Lose");
                } else {
                    p2.addWin();
                    out1.writeUTF("Lose");
                    out2.writeUTF("Win");
                }
            }

            HashMap<String, Integer> finalScores = new HashMap<>();
            finalScores.put(p1.getName(), p1.getScore());
            finalScores.put(p2.getName(), p2.getScore());

            GameResult result = new GameResult("Finished", finalScores);

            try (FileWriter writer = new FileWriter("match_results.json")) {
                new Gson().toJson(result, writer);
            }
            System.out.println("Results saved to JSON.");
            s1.close();
            s2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}