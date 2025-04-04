package org.example;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame mainFrame;
    JLabel myLabel;
    JSpinner mySpinner;
    JButton myButton;
    int panelWidth,panelHeight;
    public ConfigPanel(MainFrame mainFrame, int panelWidth, int panelHeight) {
        this.mainFrame = mainFrame;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.setOpaque(true);
        init();
        this.setSize(panelWidth, panelHeight);
    }
    private void init() {
        myLabel = new JLabel("Number of dots:");
        mySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        myButton = new JButton("New Game");
        add(myLabel);
        add(mySpinner);
        add(myButton);
    }
}
