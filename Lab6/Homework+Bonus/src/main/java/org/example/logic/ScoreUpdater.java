package org.example.logic;

import java.io.*;

public class ScoreUpdater {
    private static int currentScore;

    public ScoreUpdater() {
    }

    public static void ReadScore() {
        try {
            FileReader file = new FileReader("HighestScore.txt");
            BufferedReader reader = new BufferedReader(file);

            try {
                String line = reader.readLine();
                currentScore = Integer.parseInt(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void WriteScore(int score) {
        try {
            FileWriter file = new FileWriter("HighestScore.txt");

            try (BufferedWriter writer = new BufferedWriter(file)) {
                System.out.println("Write: " + score);
                writer.write(Integer.toString(score));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int GetCurrentScore() {
        ReadScore();
        return currentScore;
    }

    public static void Update(int score) {
        ReadScore();
        if (score > currentScore) {
            WriteScore(score);
        }

    }
}
