package org.example.logic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
public class Line implements Serializable {
    private Dot startDot;
    private Dot endDot;
    private int whichPlayer=0;
    private int edgeCost;
}
