package com.hangmangame.service;

import java.util.Arrays;

public class HangmanService {
    private String secretWord;
    private char[] hiddenWord;
    private int score;
    private int mistakes;
    private final int MAX_MISTAKES = 6;

    public HangmanService(String word) {
        this.secretWord = word.toLowerCase();
        this.hiddenWord = new char[word.length()];
        Arrays.fill(hiddenWord, '*');
        this.score = 0;
        this.mistakes = 0;
    }

    public String processGuess(char guess) {
        boolean found = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guess && hiddenWord[i] == '*') {
                hiddenWord[i] = guess;
                score++;
                found = true;
            }
        }
        if (!found)
            mistakes++;
        return String.valueOf(hiddenWord);
    }

    public boolean isGameOver() {
        return mistakes >= MAX_MISTAKES || !String.valueOf(hiddenWord).contains("*");
    }

    public int getScore() {
        return score;
    }

    public String getHiddenWord() {
        return String.valueOf(hiddenWord);
    }

    public boolean isWin() {
        return !String.valueOf(hiddenWord).contains("*");
    }
}