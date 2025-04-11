package org.example;

import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new MockDictionary();
    private final List<Player> players = new ArrayList<>();
    public Game(List<Player> players) {
        this.players.addAll(players);
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
}
