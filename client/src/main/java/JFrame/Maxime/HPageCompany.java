package JFrame.Maxime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HPageCompany extends JFrame implements ActionListener {

    JMenuItem room1;
    ArrayList<JMenu> jMenuArrayList = new ArrayList<>();
    ArrayList<JMenuItem> jMenuItemArrayList = new ArrayList<>();
    public HPageCompany(String s){
        setTitle("Page d'accueil de l'entreprise "+s);
        setLayout(new FlowLayout());
        setSize(1000, 900);
        setVisible(true);
        JMenu jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        JMenu e1 = new JMenu("Salles à la location");
        JMenu badge = new JMenu("Badge");
        JMenu building1 = new JMenu("Batiment 1");
        e1.add(building1);
        JMenu floor1 = new JMenu("Etage 1");
        building1.add(floor1);
        room1 = new JMenuItem("Salle 1");
        room1.addActionListener(this);
        floor1.add(room1);
        jMenu.add(e1);
        jMenu.add(badge);
        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==room1){
        MapperRoom mapperRoom = new MapperRoom(room1.getName());
        this.setVisible(false);}
    }
}
