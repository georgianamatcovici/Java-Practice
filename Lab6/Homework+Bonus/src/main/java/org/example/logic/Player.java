package org.example.logic;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
public class Player implements Serializable {
    private int playerScore=0;
    public void addToScore(int score) {
        playerScore+=score;
    }


}
