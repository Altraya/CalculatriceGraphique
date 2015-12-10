/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatricegraphique;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 *
 * @author Karakayn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calculatrice cal = new Calculatrice();
        
        JFrame f = new JFrame("Essai de graphe");
        JApplet applet = new Graphe();
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setSize(new Dimension(1024,768));
        f.setVisible(true);
    }

}
