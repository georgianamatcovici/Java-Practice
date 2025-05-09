package org.example.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {
    private JPanel panel;
    public MainWindow(List<Point> pointList, List<Integer> colorList) {
//        if(points!=null && points.size()>0)
//        {
//            for (Point p : points) {
//                System.out.println("Point: " + p.x + ", " + p.y);
//            }
//        }
        setTitle("Main FRame");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            panel = new Canvas(pointList, colorList);
            panel.setBackground(Color.WHITE);
            add(panel);

            setLocationRelativeTo(null);
            setVisible(true);
        }
       public Canvas getPanel() {
            return (Canvas) panel;
        }


    }

