package org.example.graphics;

import lombok.Getter;
import lombok.Setter;
import org.example.logic.Dot;
import org.example.logic.Line;
import org.example.logic.State;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Setter
public class DrawingPanel extends JPanel {
    private  MainFrame mainFrame;

    private int canvasWidth=700, canvasHeight=500;
    private int dotSize=20;
    private State state;
    private Dot previousDot;
    private boolean mousePressed=false;
    private int turn=1;
    private boolean iWantTrees=false;
    public DrawingPanel(MainFrame mainFrame, State myState){
        this.mainFrame=mainFrame;
       setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        this.setVisible(true);
        this.state=myState;
        this.state.setDrawingPanel(this);
        this.addMouseEvents();


    }
    @Override
    protected void paintComponent(Graphics graphics)
    {
        Graphics2D graphics2D= (Graphics2D) graphics;
        graphics2D.setColor(Color.lightGray);
        graphics2D.fillRect(0, 0, canvasWidth, canvasHeight);
        paintDots(graphics2D);
        paintLines(graphics2D);
       // if(iWantTrees){paintTrees(graphics2D);}
    }
    private void paintDots(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.green);
        state.getMyDots().stream().forEach(dot->{graphics2D.fillOval(dot.getXDot()-dot.getRadiusDot()/2, dot.getYDot()-dot.getRadiusDot()/2, dot.getRadiusDot(), dot.getRadiusDot());});


    }
    private void paintLines(Graphics2D graphics2D)
    {
//    if(turn==1) graphics2D.setColor(Color.blue);
//        else graphics2D.setColor(Color.red);
        state.getMyLines().stream().forEach(line->{
            if(line.getWhichPlayer()==1) graphics2D.setColor(Color.blue);
            else if(line.getWhichPlayer()==2) graphics2D.setColor(Color.red);
            graphics2D.drawLine(line.getStartDot().getXDot(), line.getStartDot().getYDot(), line.getEndDot().getXDot(), line.getEndDot().getYDot());
        });
    }
    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public State getState() {
        return state;
    }
    public void addMouseEvents()
    {// mousePressed=false;
        this.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent myEvent) {
           // System.out.println("mousePressed: ");
            if (!state.isGameOver()) {
                Dot myDot = state.findCoordinates(myEvent.getX(), myEvent.getY());
                if (myDot != null) {
                    if (mousePressed == false) {
                      //   System.out.println("mousePressed");
                        mousePressed = true;
                        previousDot = myDot;
                    } else {
                        // System.out.println("mouse Already Pressed");
                        mousePressed = false;
                        if (previousDot != null && previousDot != myDot) {
                            System.out.println("here");
                            state.addLine(previousDot, myDot, turn);
                            mainFrame.repaint();
                            state.printScores();
                            if(iWantTrees){
                                state.addLineAI();
                            }
                            else turn = 3 - turn;
                        }

                    }
                    System.out.println("mousePressed: " + mousePressed);
                }

            }
        }
    });

    }
    public void paintTrees(Graphics2D graphics2D)
    {
        graphics2D.setColor(new Color(51, 0, 102));
        state.findTree(1);
        List<Line> lines = state.getSpanningTreeLines();
       lines.stream().forEach(line->{
            graphics2D.drawLine(line.getStartDot().getXDot(), line.getStartDot().getYDot(), line.getEndDot().getXDot(), line.getEndDot().getYDot());
        });
System.out.println();
        graphics2D.setColor(new Color(0, 51, 25));
        state.findTree(2);
        lines = state.getSpanningTreeLines();
        lines.stream().forEach(line->{
            graphics2D.drawLine(line.getStartDot().getXDot(), line.getStartDot().getYDot(), line.getEndDot().getXDot(), line.getEndDot().getYDot());
        });
        System.out.println();
        state.findTree(3);
        lines = state.getSpanningTreeLines();
        lines.stream().forEach(line->{
            graphics2D.drawLine(line.getStartDot().getXDot(), line.getStartDot().getYDot(), line.getEndDot().getXDot(), line.getEndDot().getYDot());
        });

    }

}
