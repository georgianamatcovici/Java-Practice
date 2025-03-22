package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jgrapht.graph.DefaultWeightedEdge;

@AllArgsConstructor
@Getter
public class Route extends DefaultWeightedEdge {
   private double timeNeeded;
   private double safetyProbability;
}
