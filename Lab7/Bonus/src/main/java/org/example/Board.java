package org.example;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<String> listOfWords=new ArrayList<String>();
    public synchronized void addWord(Player player, String wordToAdd) {
            listOfWords.add(wordToAdd);
            System.out.println(player.getName() + ": " + wordToAdd);


    }
    @Override
    public String toString() {
        return listOfWords.toString();
    }


}
