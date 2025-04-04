package org.example.graphics;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Setter
public class ControlPanel extends JPanel {
    private MainFrame mainFrame;
    JButton exitButton=new JButton("Exit");
    JButton loadButton=new JButton("Load");
    JButton saveButton=new JButton("Save");
    public ControlPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setOpaque(true);
        init();
    }
    private void init(){
        setLayout(new GridLayout(1, 4));
        add(exitButton);
        add(loadButton);
        add(saveButton);
        addMouseEvents();

    }
    public void addMouseEvents() {
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.saveGame();
                System.out.println("Saved Game");

            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame loadedGame= mainFrame.loadGame();
                System.out.println("Loaded Game");
                //  System.out.println(loadedGame.getCanvas().getState().getMyLines().toString());
                if(loadedGame.getCanvas()==null)
                {
                    System.out.println("Can't load Game");
                }
                DrawingPanel loadedCanvas=loadedGame.getCanvas();
                mainFrame.setAll(loadedGame);
//                loadedCanvas.addMouseListener(mainFrame.getMouseAdapter());
//                loadedCanvas.addMouseMotionListener(mainFrame.getMouseMotionAdapter());
                //  System.out.println(mainFrame.getCanvas().getState().getMyLines().toString());
                mainFrame.repaint();
                mainFrame.addMouseEvents();
                // mainFrame.repaint();
            }
        });
    }
}
