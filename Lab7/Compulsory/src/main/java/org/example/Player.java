package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Player implements Runnable{
    private String name;
    private Game game;
    private boolean running=true;
    public Player(String name) { this.name = name; }
    private boolean submitWord() {
        List<Tile> extracted = game.getBag().extractTiles(5);
        if (extracted.isEmpty()) {
            return false;
        }
        String word = createWordFromTiles(extracted);
        game.getBoard().addWord(this, word);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            running = false;
        }

        return true;
    }

    @Override
    public void run() {
        while (running) {
            if (!submitWord()) {
                running = false;
            }
        }
    }


    private String createWordFromTiles(List<Tile> tiles) {
        StringBuilder word = new StringBuilder();
        for (Tile tile : tiles) {
            word.append(tile.getTileLetter());
        }
        return word.toString();
    }

}

