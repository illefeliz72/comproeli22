package com.models;

import java.util.HashMap;

public class GameResult {
    private String status;
    private HashMap<String, Integer> scores;

    public GameResult(String status, HashMap<String, Integer> scores) {
        this.status = status;
        this.scores = scores;
    }
}