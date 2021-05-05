package jframe.maxime.window;

import javax.swing.*;
import java.awt.*;

public class Legend extends JMenu {
    private JMenuItem green = new JMenuItem("équipements opérationnel");
    private JMenuItem red = new JMenuItem("équipements  non opérationnel");
    private JMenuItem blue = new JMenuItem("capteurs opérationnel");
    private JMenuItem pink = new JMenuItem("capteur non opérationnel");
    private JMenuItem gray = new JMenuItem("fenêtre ou store");
    private JMenuItem yellow = new JMenuItem("emplacement");
    public Legend(){
        add(green);
        add(red);
        add(blue);
        add(pink);
        add(gray);
        add(yellow);
        yellow.setBackground(Color.orange);
        green.setBackground(Color.GREEN);
        red.setBackground(Color.RED);
        blue.setBackground(Color.CYAN);
        pink.setBackground(Color.PINK);
        gray.setBackground(Color.GRAY);
        setText("Légende");
    }
}
