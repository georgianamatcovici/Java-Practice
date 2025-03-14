package lab3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    int flightsNumber;
    int[][] adjacencyMatrix;
    int[] flightsRunways;
    int availableRunways;
    boolean enoughRunways=true;
    //int[] colorClasses;
    int chiOfG;
    Map<Integer, List<Integer>> coloringClasses = new HashMap<>();

    public Graph(int flightsNumber, int availableRunways) {
        this.flightsNumber = flightsNumber;
        adjacencyMatrix = new int[flightsNumber][flightsNumber];
        flightsRunways = new int[flightsNumber];
        this.availableRunways = availableRunways;
        ///colorClasses = new int[flightsNumber];
    }

    public void setEdge(int u, int v) {
        adjacencyMatrix[u][v] = adjacencyMatrix[v][u] = 1;
    }

    public void printMatrix() {
        int i, j;
        for (i = 0; i < flightsNumber; i++) {
            for (j = 0; j < flightsNumber; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void greedyColoring() {
        flightsRunways[0] = 1;
        coloringClasses.putIfAbsent(1, new ArrayList<>());
        coloringClasses.get(1).add(1);
        chiOfG = 1;
        for (int i = 1; i < flightsNumber; i++) {
            int j = 0;
            int firstV = -1;
            do {
                j++;
                firstV = -1;
                for (int k = 0; k < flightsNumber; k++) {
                    if (flightsRunways[k] == j && adjacencyMatrix[k][i] == 1) {
                        firstV = k;
                        break;
                    }
                }
                if (firstV == -1) {
                    flightsRunways[i] = j;
                    coloringClasses.putIfAbsent(j, new ArrayList<>());
                    coloringClasses.get(j).add(i+1);

                }
            } while (firstV != -1 && j < chiOfG);
            if (firstV != -1) {
                flightsRunways[i] = j + 1;
                chiOfG = j + 1;
                if(chiOfG>availableRunways) {
                    System.out.println("We need more runways or reschedule flight #"+i);
                    enoughRunways=false;
                }
                coloringClasses.putIfAbsent(j+1, new ArrayList<>());
                coloringClasses.get(j+1).add(i+1);

            }
        }


    }

    public boolean hasConflict(int node, int color)
    {
        for(int j = 0; j < flightsNumber; j++)
        {
            if(adjacencyMatrix[node][j] == 1 && flightsRunways[j] == color)
                return true;
        }
        return false;
    }

    public void adjustClasses() {
        int previousColor=-1;
        int previousColorSize=0;
        for (Map.Entry<Integer, List<Integer>> mapEntry : coloringClasses.entrySet()) {
            int currentColor = mapEntry.getKey();
            int sizeOfClass = mapEntry.getValue().size();
            if (previousColor ==-1) {
                previousColor = currentColor;
                previousColorSize = sizeOfClass;

            }
            else {
                if(Math.abs(sizeOfClass-previousColorSize)>1) {
                    if(previousColorSize>sizeOfClass) {
                        ArrayList<Integer> toRemove = new ArrayList<>(previousColorSize);
                        List<Integer> previousColorNodes=coloringClasses.get(previousColor);
                        //List<Integer> currentColorNodes=coloringClasses.get(currentColor);
                        LABEL:
                        for (Integer node : previousColorNodes) {
                            if(!hasConflict(node-1, currentColor))
                            {
                                coloringClasses.get(currentColor).add(node);
                                //previousColorNodes.remove(node);
                                previousColorSize--;
                                sizeOfClass++;
                                toRemove.add(node);
                                flightsRunways[node-1]=currentColor;
                                if(Math.abs(sizeOfClass-previousColorSize)<=1) { break LABEL; }

                            }
                        }
                        for (Integer node : toRemove) {
                            previousColorNodes.remove(node);
                        }

                    }
                    else {

                        ArrayList<Integer> toRemove = new ArrayList<>(sizeOfClass);
                        List<Integer> currentColorNodes=coloringClasses.get(currentColor);
                        //List<Integer> currentColorNodes=coloringClasses.get(currentColor);
                        LABEL1:
                        for (Integer node : currentColorNodes) {
                            if(!hasConflict(node-1, previousColor))
                            {
                                coloringClasses.get(previousColor).add(node);
                                //previousColorNodes.remove(node);
                                previousColorSize++;
                                sizeOfClass--;
                                toRemove.add(node);
                                flightsRunways[node-1]=previousColor;
                                if(Math.abs(sizeOfClass-previousColorSize)<=1) { break LABEL1; }

                            }
                        }
                        for (Integer node : toRemove) {
                            currentColorNodes.remove(node);
                        }


                    }

                }
            }
        }
    }


    public void printColoring() {
        greedyColoring();
for(int i = 0; i < flightsNumber; i++) {
    System.out.print(flightsRunways[i] + " ");
}
System.out.println();
System.out.println(coloringClasses);
if(enoughRunways) {
    adjustClasses();
    System.out.println("After adjusting coloring classes:");
    System.out.println(coloringClasses);
}
    }
}
