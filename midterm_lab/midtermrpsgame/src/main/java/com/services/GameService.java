package com.services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.models.MatchResult;
import com.models.Player;

public class GameService {
    private static final String LEADERBOARD_FILE = "leaderboard.txt";

    public String compareMoves(Player p1, Player p2, int m1, int m2) {
        if (m1 == m2)
            return "TIE TIE ITS A TIE!";

        // 0=Rock, 1=Paper, 2=Scissors
        if ((m1 == 0 && m2 == 2) || (m1 == 1 && m2 == 0) || (m1 == 2 && m2 == 1)) {
            p1.incrementScore();
            return p1.getName() + " wins the round!";
        } else {
            p2.incrementScore();
            return p2.getName() + " wins the round!";
        }
    }

    public MatchResult determineMatchWinner(Player p1, Player p2) {
        String winner = "Draw";
        if (p1.getScore() > p2.getScore())
            winner = p1.getName();
        if (p2.getScore() > p1.getScore())
            winner = p2.getName();

        return new MatchResult(p1, p2, winner);
    }

    public void saveMatch(MatchResult result) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LEADERBOARD_FILE, true))) {
            writer.println(result.getSummary());
        } catch (IOException e) {
            System.err.println("Failed to save leaderboard: " + e.getMessage());
        }
    }

    public String getLeaderboard() {
        try {
            if (Files.exists(Paths.get(LEADERBOARD_FILE))) {
                return new String(Files.readAllBytes(Paths.get(LEADERBOARD_FILE)));
            }
        } catch (IOException e) {
            return "Error reading leaderboard.";
        }
        return "No previous matches recorded.";
    }
}
