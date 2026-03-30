package com.models;

public class Player extends User {
    private static final long serialVersionUID = 1L;
    private int score;

    public Player(String name) {
        super(name);
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }
}

