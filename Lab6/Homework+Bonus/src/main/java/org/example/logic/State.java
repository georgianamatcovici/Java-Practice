package org.example.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import org.example.graphics.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class State implements Serializable {
    private Player firstPlayer;
    private Player secondPlayer;
    private List<Dot> myDots= new ArrayList<>();
    private List<Line> myLines= new ArrayList<>();
    private List<Line> spanningTreeLines= new ArrayList<>();
   private  int numberOfDots=30;
    private int width=700, height=500;
    private Graph myGraph;
    boolean gameOver=false;
    DrawingPanel drawingPanel;
    private int difficultyLevel;
    public State()
    {
        firstPlayer = new Player();
        secondPlayer = new Player();
        myGraph=new Graph(100);
    }
    public void generateDots()
    {
        for(int i=0; i<numberOfDots; i++)
        {
            int randomX = (int) (Math.random()*(width-40))+20;
            int randomY = (int) (Math.random()*(height-40))+20;
            myDots.add(new Dot(i, randomX, randomY, 15));
        }

    }

   public Dot findCoordinates(int xToFind, int yToFind)
    {
        return myDots.stream()
                .filter(dot->(xToFind>=dot.getXDot()-dot.getRadiusDot()/2&& xToFind<=dot.getXDot()+dot.getRadiusDot()/2)&&(yToFind>=dot.getYDot()-dot.getRadiusDot()/2&& yToFind<=dot.getYDot()+dot.getRadiusDot()/2))
                .findAny().orElse(null);

    }
    public void addLine(Dot startDot, Dot endDot, int whichPlayer)
    { System.out.println("addLine");
        if(myDots.size()>=numberOfDots-1) {
            gameOver = checkIfAllConected();
            System.out.println("Game over: " + gameOver);
        }
        if(!gameOver) {

            int euclidianDistance =(int) Math.sqrt((endDot.getXDot() - startDot.getXDot()) * (endDot.getXDot() - startDot.getXDot()) + (endDot.getYDot() - startDot.getYDot()) * (endDot.getYDot() - startDot.getYDot()));
            myLines.add(new Line(startDot, endDot, whichPlayer, euclidianDistance));
            addScore(whichPlayer, euclidianDistance);
            myGraph.addLine(startDot.getIdDot(), endDot.getIdDot(), whichPlayer);
        }
        else {
            ScoreUpdater.Update(firstPlayer.getPlayerScore());
            ScoreUpdater.Update(secondPlayer.getPlayerScore());
            exportBoard(drawingPanel);
            saveGame();
            State newState = this.loadState();

        }
    }
    public void setNumberOfDots(int numberOfDots, int width, int height)
    {
        this.numberOfDots = numberOfDots;
        myGraph.setCountDots(numberOfDots);
        myGraph.setCountLines(0);
        myGraph.initializeAll();
        myDots.clear();
        myLines.clear();
        generateDots();
        gameOver=false;
    }
    public void addScore(int player, int score)
    {
        if(player==1)
            firstPlayer.addToScore(score);
        else
            secondPlayer.addToScore(score);
    }
    public void printScores()
    {
        System.out.println("1: "+firstPlayer);
        System.out.println("2: "+secondPlayer);
    }
    public boolean checkIfAllConected()
    {
        return myGraph.allVisited();


    }
    public void exportBoard(DrawingPanel drawingPanel) {
        BufferedImage imageOfBoard = new BufferedImage(drawingPanel.getCanvasWidth(), drawingPanel.getCanvasHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2d = imageOfBoard.createGraphics();
        drawingPanel.paint(graphics2d);
        graphics2d.dispose();

        try {
            ImageIO.write(imageOfBoard, "PNG", new File("gameBoard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDrawingPanel(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public void saveGame() {
        try (var oos = new ObjectOutputStream(
                new FileOutputStream("./game.bin"))) {
            if (!(this instanceof Serializable)) {
                throw new IOException("Repository not serializable.");
            }
            oos.writeObject(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public State loadState() {
        State myLoadedState=null;
        try(FileInputStream fis = new FileInputStream("./game.bin")) {
            ObjectInputStream input=new ObjectInputStream(fis);
            myLoadedState=(State) input.readObject();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        if (myLoadedState == null) {
            System.out.println("Couldn't Deserialize.");
        }
        return myLoadedState;
    }

    public void setSpanningTreeLines(List<Line> spanningTreeLines) {
        this.spanningTreeLines = spanningTreeLines;
    }
    public void findTree(int treeLevel)
    {
        myGraph.solveKruskal(this, treeLevel);
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        findTree(difficultyLevel);
        System.out.println();
        spanningTreeLines.stream()
                .forEach(line -> {System.out.println(line.getStartDot().getIdDot()+" "+line.getEndDot().getIdDot()+" "+ line.getWhichPlayer());});
       System.out.println();
    }
    public void addLineAI()
    {
  Optional<Line> newLine=spanningTreeLines.stream()
          .filter(myLine->myGraph.adjacencyMatrix[myLine.getStartDot() .getIdDot()][myLine.getEndDot().getIdDot()]==0)
          .findFirst();

  if(newLine.isEmpty())
      System.out.println("No line found");
  else this.addLine(newLine.get().getStartDot(), newLine.get().getEndDot(), 2);




    }
    public void initializeGame()
    {
        myGraph.initializeAll();
    }
}
