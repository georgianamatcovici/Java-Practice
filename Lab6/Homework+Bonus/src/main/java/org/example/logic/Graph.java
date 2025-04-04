package org.example.logic;

import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
public class Graph implements Serializable {
    int countDots, countLines;
    int adjacencyMatrix[][];
    boolean isVisited[];
    int rootOfNode[];
    public Graph() {}
    public Graph(int countDots) {
        this.countDots = countDots;
        this.countLines = 0;
        adjacencyMatrix = new int[countDots][countDots];
        isVisited = new boolean[countDots];
        rootOfNode = new int[countDots];
    }

    public void addLine(int xDot, int yDot, int whichPlayer) {
        System.out.println("Added line"+xDot+" "+yDot+" "+whichPlayer);
        countLines++;
        adjacencyMatrix[xDot][yDot] = adjacencyMatrix[yDot][xDot] = whichPlayer;
    }

    public void depthFirstSearch(int startNode) {
        System.out.println(startNode);
        isVisited[startNode] = true;
        for (int i = 0; i < countDots; i++) {
            if (adjacencyMatrix[startNode][i]!=0 && !isVisited[i]) {
                isVisited[i] = true;
                depthFirstSearch(i);
            }
        }


    }

    public boolean allVisited() {
       // printAdjacencyMatrix();
        for (int i = 0; i < countDots; i++) {
            isVisited[i] = false;
        }
        depthFirstSearch(0);
        for (int i = 0; i < countDots; i++)
            if (!isVisited[i]) return false;
        return true;
    }

    public void printAdjacencyMatrix() {
        System.out.println(countDots + " " + countLines);
        for (int i = 0; i < countDots; i++) {
            for (int j = 0; j < countDots; j++) {
                System.out.print(adjacencyMatrix[i][j]+" ");

            }
            System.out.println();
        }
    }
    public void initializeAll() {
        for (int i = 0; i < countDots; i++) {
            for (int j = 0; j < countDots; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
        for (int i = 0; i < countDots; i++) {
            isVisited[i] = false;
        }
    }
    public List<Line> computeAllCosts(State  myState)
    {List<Dot> allDots=myState.getMyDots();
        List<Line> allLines=new ArrayList<>();
      for(Dot startDot:allDots)
      {
          for(Dot endDot:allDots) {
              if(!startDot.equals(endDot)) {
                  int euclidianDistance = (int) Math.sqrt((endDot.getXDot() - startDot.getXDot()) * (endDot.getXDot() - startDot.getXDot()) + (endDot.getYDot() - startDot.getYDot()) * (endDot.getYDot() - startDot.getYDot()));
                  allLines.add(new Line(startDot, endDot, 3, euclidianDistance));
              }
          }
      }
      return allLines;


    }
    public void solveKruskal(State myState, int treeLevel){
        List<Line> treeLines=new ArrayList<>();
        List<Line> sortedLines=
                computeAllCosts(myState).stream()
                .sorted(Comparator.comparingInt(Line::getEdgeCost))
                        .collect(Collectors.toList());
        if(treeLevel==1 || treeLevel==2)
        {
            Line myEdge=sortedLines.get(0);
            int firstNode=myEdge.getStartDot().getIdDot();
            int secondNode=myEdge.getEndDot().getIdDot();
            sortedLines.removeIf(currLine->((currLine.getStartDot().getIdDot()==secondNode&&currLine.getEndDot().getIdDot()==firstNode)||(currLine.getStartDot().getIdDot()==firstNode&&currLine.getEndDot().getIdDot()==secondNode)));
            System.out.println(firstNode+" "+secondNode);
        }
        if(treeLevel==1)
        {
            Line myEdge=sortedLines.get(0);
            int firstNode=myEdge.getStartDot().getIdDot();
            int secondNode=myEdge.getEndDot().getIdDot();
            sortedLines.removeIf(currLine->((currLine.getStartDot().getIdDot()==secondNode&&currLine.getEndDot().getIdDot()==firstNode)||(currLine.getStartDot().getIdDot()==firstNode&&currLine.getEndDot().getIdDot()==secondNode)));
            System.out.println(firstNode+" "+secondNode);
        }
        for(int i=0; i<countDots; i++){
            rootOfNode[i]=i;
        }
        int edgesAdded=0, totalCost=0;
        for(int i=0; i<sortedLines.size() && edgesAdded<=countDots-1; i++) {
            Line currentLine=sortedLines.get(i);
            int firstId=currentLine.getStartDot().getIdDot();
            int secondId=currentLine.getEndDot().getIdDot();
            if(rootOfNode[firstId]!=rootOfNode[secondId]){
                totalCost+=currentLine.getEdgeCost();
                treeLines.add(currentLine);
                edgesAdded++;
                //System.out.println("Edge "+firstId+" "+secondId+" "+totalCost);
                int rootFirstId=rootOfNode[firstId];
                int rootSecondId=rootOfNode[secondId];
                for(int j=0; j<countDots; j++){
                    if(rootOfNode[j]==rootSecondId){
                        rootOfNode[j]=rootFirstId;
                    }
                }
            }

        }
        myState.setSpanningTreeLines(treeLines);
        }





//    public void findSpanningTrees()
//    {
//
//    }
}
