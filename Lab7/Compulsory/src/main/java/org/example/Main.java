package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Player> myPlayers = Arrays.asList(new Player("Player1"), new Player("Player2"));

        Game game = new Game(myPlayers);

        for (Player player : myPlayers) {
            player.setGame(game);
        }

        game.startGame();

    }
}