package jFrame.maxime.button;

import jFrame.maxime.window.WindowEquipement;
import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Equipement extends JButton implements ActionListener {
    public String name;
    final int WIDTH=13;
    final int HEIGHT=13;
    public boolean etat;
    public int id;
    ArrayList<String> result;
    public String roomName;
    public Equipement(String name, int x,int y,int id,String roomName){
        this.roomName = roomName;
        this.id=id;
        this.name = name;
        addActionListener(this);
        getEtat();
        setBounds(x,y,WIDTH,HEIGHT);
    }
    public void getEtat() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("equipement");
        stringArrayList.add("etat");
        stringArrayList.add("" + id);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        etat = result.get(0).equals("t");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WindowEquipement windowEquipement =new WindowEquipement(this);
    }
}
