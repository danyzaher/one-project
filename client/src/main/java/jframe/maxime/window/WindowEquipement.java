package jframe.maxime.window;

import jframe.julien.ElectroChromaManuHigh;
import jframe.maxime.button.Equipement;
import jframe.julien.ElectroChromaManuOpa;
import jframe.maxime.button.GoBackButton;
import jframe.maxime.button.GoBackMenu;
import socket.CCSocketTCPbis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WindowEquipement extends JFrame implements ActionListener {
    public static Logger logger = LoggerFactory.getLogger("WindowEquipement");
    Equipement equipement;
    JButton delete;
    JButton move;
    JButton information;
    GoBackButton goBackButton;
    public WindowEquipement(Equipement equipement){
        this.equipement = equipement;
        setTitle(equipement.name+" "+equipement.id);
        setVisible(true);
        setSize(500,600);
        setLayout(new FlowLayout());
        delete = new JButton("Retirer l'equipement");
        delete.setBounds(50,100,30,50);
        add(delete);
        delete.addActionListener(this);
        move = new JButton("Deplacer l'equipement");
        move.setBounds(50,200,30,50);
        add(move);
        if(equipement.name.equals("fenêtre électrochromatique") | equipement.name.equals("Store")){
        information = new JButton("Information à propos de l'equipement");
        information.setBounds(50,300,30,50);
        add(information);
        information.addActionListener(this);}

    }
    public void newGoBack(JFrame j){
        goBackButton = new GoBackButton(this,j);
        add(goBackButton);
    }
    public void delete(){
        logger.info("begin getEquipement");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("delete");
        stringArrayList.add("be_present");
        stringArrayList.add("equipement");
        stringArrayList.add(equipement.id+"");
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==information){
            if(equipement.name.equals("fenêtre électrochromatique")){
            ElectroChromaManuOpa electroChromaManuOpa = new ElectroChromaManuOpa(""+equipement.id);}
            if(equipement.name.equals("Store")){
                ElectroChromaManuHigh electroChromaManuHigh = new ElectroChromaManuHigh(equipement.id+"");
            }
            this.setVisible(false);
        }
        if (e.getSource()==delete){
            String s = equipement.roomName;
            delete();
            MapperRoom m= new MapperRoom(s);
            this.setVisible(false);
        }
    }
}
