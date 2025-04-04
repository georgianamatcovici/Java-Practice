package org.example;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    final MainFrame mainFrame;
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
    }
}
