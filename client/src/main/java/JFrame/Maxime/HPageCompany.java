package JFrame.Maxime;

import Socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HPageCompany extends JFrame implements ActionListener {

    JMenuItem room1;
    String title;
    ArrayList<JMenu> jMenuArrayList = new ArrayList<>();
    ArrayList<JMenuItem> jMenuItemArrayList = new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();
    public HPageCompany(String s){
        title = s;
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
    public void getMenu(){
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add(title);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==room1){
        MapperRoom mapperRoom = new MapperRoom(room1.getName());
        this.setVisible(false);}
    }
}
