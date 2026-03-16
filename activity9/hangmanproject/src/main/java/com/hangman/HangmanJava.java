package com.hangman;

import java.util.*;
import java.io.*;
import com.google.gson.Gson;

public class HangmanJava {
    static Scanner input = new Scanner(System.in);
    static String playerName;

    public static void main(String[] args) {
        String[] words = loadWords();
        ArrayList<User> users = loadUsers();
        System.out.println("1. Sign In");
        System.out.println("2. Register");

        System.out.print("Choice: ");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {

            System.out.print("Username: ");
            playerName = input.nextLine();

        } else {

            System.out.print("Create username: ");
            playerName = input.nextLine();

            users.add(new User(playerName, 0));
            saveUsers(users);

        }

        int score = playGame(words);

        for (User u : users) {
            if (u.name.equalsIgnoreCase(playerName)) {
                u.score += score;
            }
        }

        saveUsers(users);
        showLeaderboard(users);

    }

    public static String[] loadWords() {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("words.json");
            WordList list = gson.fromJson(reader, WordList.class);
            reader.close();
            return list.words;
        } catch (Exception e) {
            System.out.println("Error loading words");
        }
        return new String[0];
    }

    public static ArrayList<User> loadUsers() {

        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("users.json");
            UserList list = gson.fromJson(reader, UserList.class);
            reader.close();
            return list.users;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void saveUsers(ArrayList<User> users) {

        try {
            Gson gson = new Gson();
            Writer writer = new FileWriter("users.json");
            UserList list = new UserList();
            list.users = users;
            gson.toJson(list, writer);
            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving users");
        }

    }

    public static int playGame(String[] words) {
        String word = words[(int) (Math.random() * words.length)];
        char[] hidden = "*".repeat(word.length()).toCharArray();
        int mistakes = 0;
        int score = 0;

        while (mistakes < 6 && String.valueOf(hidden).contains("*")) {

            System.out.println("Word: " + String.valueOf(hidden));
            System.out.print("Guess letter: ");
            char guess = input.next().charAt(0);
            boolean correct = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    hidden[i] = guess;
                    score++;
                    correct = true;
                }
            }

            if (!correct) {
                mistakes++;
                System.out.println("Wrong guess");
            }
        }

        if (!String.valueOf(hidden).contains("*")) {
            System.out.println("You guessed the word!");
        } else {
            System.out.println("Game Over. Word was: " + word);
        }
        return score;
    }
    static void showLeaderboard(ArrayList<User> users) {
        users.sort((a, b) -> b.score - a.score);
        System.out.println("\nLEADERBOARD");
        for (User u : users) {
            System.out.println(u.name + " - " + u.score);
        }
    }
}
