package com.models;

import java.io.Serializable;

public class MatchResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private Player player1;
    private Player player2;
    private String overallWinner;

    public MatchResult(Player p1, Player p2, String winner) {
        this.player1 = p1;
        this.player2 = p2;
        this.overallWinner = winner;
    }

    public String getSummary() {

            return String.format("""
                  Match: %s (%d) vs %s (%d) - 
                  OVERALL WINNER : %s 
            """,player1.getName(), player1.getScore()
            ,player2.getName(), player2.getScore(), overallWinner);
    }
}
