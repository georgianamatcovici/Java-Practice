package org.example.graphics;

import lombok.Setter;
import org.example.logic.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Setter
public class ConfigPanel extends JPanel {
    private MainFrame mainFrame;
    JLabel myLabel;
    JSpinner mySpinner;
    JSpinner difficultySpinner;
    JButton myButton, generateButton;
    State state;
    int panelWidth,panelHeight;
    public ConfigPanel(MainFrame mainFrame, int panelWidth, int panelHeight, State myState) {
        this.mainFrame = mainFrame;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        state=myState;
        this.setOpaque(true);
        init();
        this.setSize(panelWidth, panelHeight);
    }
    private void init() {
        myLabel = new JLabel("Number of dots:");
        mySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        myButton = new JButton("New Game Human");
        generateButton = new JButton("New Game AI");
        difficultySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
        addMouseEvents();
        add(myLabel);
        add(mySpinner);
        add(myButton);
        add(generateButton);
        add(difficultySpinner);
    }
    public void addMouseEvents() {
        myButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                state.setNumberOfDots(Integer.parseInt(mySpinner.getValue().toString()), panelWidth, panelHeight);
                System.out.println(state.getNumberOfDots());
                mainFrame.getCanvas().setMousePressed(false);
                mainFrame.getCanvas().setIWantTrees(false);
                mainFrame.getCanvas().getState().initializeGame();
                mainFrame.repaint();
            }
        });
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                state.setNumberOfDots(Integer.parseInt(mySpinner.getValue().toString()), panelWidth, panelHeight);
                state.setDifficultyLevel(Integer.parseInt(difficultySpinner.getValue().toString()));
                mainFrame.getCanvas().setMousePressed(false);
                mainFrame.getCanvas().setIWantTrees(true);
                mainFrame.getCanvas().getState().initializeGame();
                mainFrame.repaint();

            }
        });
    }
}
