/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatricegraphique;


import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Graphe extends JApplet {
 
    final static Color bg = Color.white;
    final static Color fg = Color.black;
    final static Double offset = 20.0;
 
    public void init() {
        setBackground(bg);
        setForeground(fg);
    }
    
    public void coord(Point2D.Double in)
    {
        Dimension d = getSize();
        in.setLocation(in.getX()+offset, -in.getY()+d.height-offset);
    }
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = getSize();
        g.clearRect(0, 0, d.width, d.height);

        Point2D.Double origin = new Point2D.Double(offset,d.height-offset);
        Point2D.Double x_grid = new Point2D.Double(offset,offset);
        Point2D.Double y_grid = new Point2D.Double(d.width-offset, d.height-offset);
        g2.draw(new Line2D.Double(origin,x_grid));
        g2.draw(new Line2D.Double(origin,y_grid));
        Point2D.Double point = new Point2D.Double(0,0);

        for(double i = 0; i < 5000; i++)
        {
            point.setLocation(i/5,10*computeFunction(i/20));
            coord(point);
            g2.draw(new Ellipse2D.Double(point.getX(),point.getY(), 1, 1));
        }
    }
    
    public double computeFunction(double x)
    {
        return 10+(2+x/200)*Math.sin(x)+x*x/1000;
    }
 
}