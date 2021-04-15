package JFrame.Maxime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HPageEntreprise extends JFrame implements ActionListener {

    JMenuItem e4;
    public HPageEntreprise(String s){
        setTitle("Page d'accueil de l'entreprise "+s);
        setLayout(new FlowLayout());
        setSize(1000, 900);
        setVisible(true);
        JMenu jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        JMenu e1 = new JMenu("Salles à la location");
        JMenu paramètre = new JMenu("Paramètre");
        JMenu badge = new JMenu("Badge");
        JMenu batiment1 = new JMenu("Batiment 1");
        e1.add(batiment1);
        JMenu e3 = new JMenu("Etage 1");
        batiment1.add(e3);
        e4 = new JMenuItem("Salle 1");
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
        if(e.getSource()==e4){
        MapperSalle mapperSalle = new MapperSalle(e4.getName());
        this.setVisible(false);}
    }
}
