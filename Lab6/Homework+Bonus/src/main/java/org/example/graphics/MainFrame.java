package org.example.graphics;

import lombok.Getter;
import org.example.logic.State;

import javax.swing.*;

import java.awt.*;
import java.io.*;

public class MainFrame extends JFrame {
    private int frameWidth, frameHeight;
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    State state;
    public MainFrame(int frameWidth, int frameHeight) {
        super("Main Frame");
        this.frameWidth=frameWidth;
        this.frameHeight=frameHeight;
        state=new State();
        configPanel=new ConfigPanel(this, 700, 200, state);
        controlPanel=new ControlPanel(this);
        canvas = new DrawingPanel(this, state);
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
    public DrawingPanel getCanvas() {
        return canvas;
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void saveGame() {
        try (var oos = new ObjectOutputStream(
                new FileOutputStream("./game.bin"))) {
            if (!(this instanceof Serializable)) {
                throw new IOException("Frame not serializable.");
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
    public MainFrame loadGame() {
        MainFrame myLoadedState=null;
        try(FileInputStream fis = new FileInputStream("./game.bin")) {
            ObjectInputStream input=new ObjectInputStream(fis);
            myLoadedState=(MainFrame) input.readObject();
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


    public void setAll(MainFrame newFrame) {
        DrawingPanel newCanvas=newFrame.getCanvas();
        ConfigPanel newConfig=newFrame.getConfigPanel();
        ControlPanel newControl=newFrame.getControlPanel();
        newCanvas.setMainFrame(this);
        newConfig.setMainFrame(this);
        newControl.setMainFrame(this);
        this.getContentPane().remove(canvas);
        this.getContentPane().remove(controlPanel);
        this.getContentPane().remove(configPanel);
       // newCanvas.addMouseEvents();
        newConfig.addMouseEvents();
        newControl.addMouseEvents();
        this.canvas = newCanvas;
        this.controlPanel = newControl;
        this.configPanel = newConfig;
        add(this.canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(configPanel, BorderLayout.NORTH);
        revalidate();
    }
    public void addMouseEvents() {
        canvas.addMouseEvents();
    }
}
