package JFrame.Maxime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HPageEntreprise extends JFrame implements ActionListener {

    public HPageEntreprise(String s){
        setTitle("page d'accueil de l'entreprise "+s);
        setLayout(new FlowLayout());
        setSize(600, 500);
        setVisible(true);
        JMenu jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        JMenu e1 = new JMenu("salles à la location");
        JMenu paramètre = new JMenu("paramètre");
        JMenu badge = new JMenu("badge");
        JMenu batiment1 = new JMenu("batiment 1");
        e1.add(batiment1);
        JMenu e3 = new JMenu("étage 1");
        batiment1.add(e3);
        JMenuItem e4 = new JMenuItem("salle 1");
        e4.addActionListener(this);
        e3.add(e4);
        jMenu.add(e1);
        jMenu.add(paramètre);
        jMenu.add(badge);
        this.setJMenuBar(jMenuBar);
    }
    public static void main(String[] args) {
        HPageEntreprise hPageEntreprise = new HPageEntreprise("Fritel Inc");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapperSalle mapperSalle = new MapperSalle(3);
    }
}
