package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Player> myPlayers = Arrays.asList(new Player("Player1"), new Player("Player2"));

        Game game = new Game(myPlayers);
     //  new Dictionary().displayNodes();
        long startTime = System.nanoTime();
        new Dictionary().findPrefix("style");
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Time: " + elapsedTime/1_000_000 + " ms");


        for (Player player : myPlayers) {
            player.setGame(game);
        }

        game.startGame();
        TimeKeeper myTimer=new TimeKeeper(game, 15);
        myTimer.start();

   }
}