package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingPanel extends JPanel {
private final MainFrame mainFrame;
private int canvasWidth=700, canvasHeight=500;
private int dotSize=20;
public DrawingPanel(MainFrame mainFrame){
    this.mainFrame=mainFrame;
    setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    this.setVisible(true);
}
@Override
    protected void paintComponent(Graphics graphics)
{
    Graphics2D graphics2D= (Graphics2D) graphics;
    graphics2D.setColor(Color.lightGray);
    graphics2D.fillRect(0, 0, canvasWidth, canvasHeight);
    paintDots(graphics2D);
}
private void paintDots(Graphics2D graphics2D)
{
    graphics2D.setColor(Color.green);
for(int i=0; i<30; i++){
    int randomX = (int) (Math.random()*(canvasWidth-10));
    int randomY = (int) (Math.random()*(canvasHeight-10));
    graphics2D.fillOval(randomX, randomY, 2*5, 2*5);
}
}
}
