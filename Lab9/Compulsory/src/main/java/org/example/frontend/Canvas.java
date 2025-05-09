package org.example.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Canvas extends JPanel {
    private List<Point> points;
    private List<Integer> colors;

    public Canvas(List<Point> points, List<Integer> colors) {
        this.points = points;
        this.colors = colors;
              if(points!=null && points.size()>0)
                {
                    for (Point p : points) {
                        System.out.println("Point: " + p.x + ", " + p.y);
                    }
                }
        if(colors!=null && colors.size()>0)
        {
            for (Integer p : colors) {
                System.out.println(p);
            }
        }
    }

    public void addPoint(Point p) {
        points.add(p);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        var iterator=colors.iterator();
        if (points!=null) {
            for (Point p : points) {
                if(iterator.hasNext())
                {
                    int currentColor=iterator.next();
                    float hue = (currentColor * 37) % 360 / 360f;
                    Color color = Color.getHSBColor(hue, 0.8f, 0.9f);
                    g.setColor(color);
                }
                g.fillOval(p.x - 3, p.y - 3, 6, 6);
            }
        }
    }
}
