package org.example.logic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
public class Dot implements Serializable {
    private int idDot;
    private int xDot;
    private int yDot;
    private int radiusDot;
}
