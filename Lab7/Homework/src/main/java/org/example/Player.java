package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Getter
@Setter
public class Player implements Runnable{
    private String name;
    private Game game;
    private boolean running=true;
    private List<Tile> extractedTiles;
    private int currentScore=0;
    private ListIterator<Tile> iterator;
    private List<Tile> tilesToDiscard;

    public Player(String name) { this.name = name; }
    public void extractWords(int k)
    {
        extractedTiles = game.getBag().extractTiles(k);

    }
    private boolean submitWord() {
        extractedTiles = game.getBag().extractTiles(7);
        if (extractedTiles.isEmpty()) {
            return false;
        }
        String word = createWordFromTiles(extractedTiles);
        if(word!=null) {
            game.getBoard().addWord(this, word);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }

        }
        game.nextTurn();
        synchronized(game) {
           game.notifyAll();
        }
        return true;

    }

    @Override
    public void run() {
        while (running) {
            if(game.isGameOver()) {
                break;
            }
            synchronized(game) {
                while (!game.isGameOver() && game.getCurrentPlayer() != this) {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if (!submitWord()) {
                running = false;
                game.endGame();
            }
        }
    }



    private String createWordFromTiles(List<Tile> tiles) {

        StringBuilder wordBuilder= new StringBuilder();
        currentScore=0;
        int count=0;
        for (Tile tile : tiles) {
            wordBuilder.append(tile.getTileLetter());
            currentScore += tile.getTileLetter();
            count++;
        }
        iterator = tiles.listIterator(tiles.size());
        tilesToDiscard = new ArrayList<>();

            while(count>=3) {
                    String wordCreated = wordBuilder.toString();
                    //System.out.println(name+" "+wordCreated);
                    if (game.getDictionary().findWord(wordCreated) != -1) {
                        if(tilesToDiscard.size()>0) {
                            game.getBag().addTiles(tilesToDiscard);
                        }
                        return wordCreated;
                    }
                    wordBuilder.deleteCharAt(wordCreated.length()-1);
                    Tile currentTile=iterator.previous();
                    tilesToDiscard.add(currentTile);
                    currentScore -= currentTile.getTileLetter();
                    count--;

            }
            game.getBag().addTiles(tiles);

    return null;
    }

}

