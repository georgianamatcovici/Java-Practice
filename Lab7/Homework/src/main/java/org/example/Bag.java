package org.example;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    List<Tile> tiles=new ArrayList<>();
    public Bag() {
        for (char c = 'a'; c < 'z'; c++) {
            for(int i=1; i<10; i++) {
                tiles.add(new Tile(c, i));
            }
        }

    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tiles.isEmpty()) {
                break;
            }
            int randomIndex = (int)(Math.random()*tiles.size());
            extracted.add(tiles.remove(randomIndex));
        }
        return extracted;
    }
    public synchronized void addTiles(List<Tile> tiles) {
        this.tiles.addAll(tiles);
    }
}
