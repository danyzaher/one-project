package jframe.maxime.window;

import javax.swing.*;
import java.awt.*;

public class Legende extends JMenu {
    JMenuItem green = new JMenuItem("équipements opérationnel");
    JMenuItem red = new JMenuItem("équipements  non opérationnel");
    JMenuItem blue = new JMenuItem("capteurs opérationnel");
    JMenuItem pink = new JMenuItem("capteur non opérationnel");
    JMenuItem gray = new JMenuItem("fenêtre ou store");
    public Legende(){
        add(green);
        add(red);
        add(blue);
        add(pink);
        add(gray);
        green.setBackground(Color.GREEN);
        red.setBackground(Color.RED);
        blue.setBackground(Color.CYAN);
        pink.setBackground(Color.PINK);
        gray.setBackground(Color.GRAY);
        setText("Légende");
    }
}
