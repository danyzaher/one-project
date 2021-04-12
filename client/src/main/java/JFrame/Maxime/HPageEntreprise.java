package JFrame.Maxime;

import javax.swing.*;
import java.awt.*;

public class HPageEntreprise extends JFrame {

    public HPageEntreprise(){
        setTitle("page d'accueil de l'entreprise");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(600, 500);
        setVisible(true);
        JMenu jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        JMenu e1 = new JMenu("salles à la location");
        JMenu e2 = new JMenu("batiment 1");
        e1.add(e2);
        JMenu e3 = new JMenu("étage 1");
        e2.add(e3);
        JMenuItem e4 = new JMenuItem("salle 1");
        e3.add(e4);
        jMenu.add(e1);
        this.setJMenuBar(jMenuBar);
    }
    public static void main(String[] args) {
        HPageEntreprise hPageEntreprise = new HPageEntreprise();
    }
}
