package org.example;

import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new Dictionary();
    private final List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private int currentPlayerIndex;
    private boolean isGameOver=false;
    public Game(List<Player> players) {
        this.players.addAll(players);
        this.currentPlayer = players.get(0);
        currentPlayerIndex = 0;
    }
    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }
    public void startGame() {
        for (Player player : players) {
            new Thread(player).start();
        }
    }
    public void nextTurn()
    {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }
    public synchronized void endGame() {

       if(!isGameOver) {
           isGameOver = true;
           displayWinner();
           this.notifyAll();
       }
    }
    private void displayWinner() {
       Player winner= players.stream().max((a, b)->a.getCurrentScore()-b.getCurrentScore()).get();
       int maxScore=winner.getCurrentScore();
       System.out.println("The winner is: "+winner.getName()+" with: "+maxScore+" points");


    }
}
