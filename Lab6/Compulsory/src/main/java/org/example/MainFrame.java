package org.example;

import javax.swing.*;

import java.awt.*;

import static javax.swing.SwingConstants.*;

public class MainFrame extends JFrame {
    private int frameWidth, frameHeight;
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    public MainFrame(int frameWidth, int frameHeight) {
        super("Main Frame");
        this.frameWidth=frameWidth;
        this.frameHeight=frameHeight;
        configPanel=new ConfigPanel(this, 700, 200);
        controlPanel=new ControlPanel(this);
        canvas = new DrawingPanel(this);
        init();
    }
    public void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
       // add(configPanel, TOP);
       // add(controlPanel, 200);
        this.setSize(frameWidth, frameHeight);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = screenSize.width / 2 - this.frameWidth / 2;
        int y = screenSize.height / 2 - this.frameHeight / 2;
        this.setLocation(x,y);
    }
}
