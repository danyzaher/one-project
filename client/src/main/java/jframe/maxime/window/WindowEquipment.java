package jframe.maxime.window;

import jframe.julien.ElectroChromaManuHigh;
import jframe.maxime.button.Equipment;
import jframe.julien.ElectroChromaManuOpa;
import jframe.maxime.button.GoBackButton;
import socket.CCSocketTCPbis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WindowEquipment extends JFrame implements ActionListener {
    private static Logger logger = LoggerFactory.getLogger("WindowEquipment");
    Equipment equipment;
    private JButton delete;
    private JButton move;
    private JButton information;
    private GoBackButton goBackButton;
    public WindowEquipment(Equipment equipment){
        this.equipment = equipment;
        setTitle(equipment.name+" "+ equipment.id);
        setVisible(true);
        setSize(500,600);
        setLayout(new FlowLayout());
        delete = new JButton("Retirer l'équipement");
        delete.setBounds(50,100,30,50);
        add(delete);
        delete.addActionListener(this);
        move = new JButton("Déplacer l'équipement");
        move.setBounds(50,200,30,50);
        add(move);
        move.addActionListener(this);
        if(equipment.name.equals("fenetre electrochromatique") | equipment.name.equals("Store")){
        information = new JButton("Information à propos de l'équipement");
        information.setBounds(50,300,30,50);
        add(information);
        information.addActionListener(this);}
        JLabel jLabel= new JLabel("position en x de l'équipement = " + equipment.x );
        JLabel jLabel2 = new JLabel("position en y de l'équipement = "+ equipment.y);
        add(jLabel);
        add(jLabel2);

    }
    public void newGoBack(JFrame j){
        goBackButton = new GoBackButton(this,j);
        add(goBackButton);
    }
    private void delete(){
        logger.info("begin delete");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("delete");
        stringArrayList.add("be_present");
        stringArrayList.add("equipment");
        stringArrayList.add(equipment.id+"");
        new CCSocketTCPbis(stringArrayList);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==information){
            if(equipment.name.equals("fenetre electrochromatique")){
                ElectroChromaManuOpa electroChromaManuOpa = new ElectroChromaManuOpa(equipment);
                electroChromaManuOpa.newGoBack(this);
            }
            if(equipment.name.equals("Store")){
                ElectroChromaManuHigh electroChromaManuHigh = new ElectroChromaManuHigh(equipment);
                electroChromaManuHigh.newGoBack(this);
            }
            this.setVisible(false);
        }
        if (e.getSource()==delete){
            String s = equipment.roomName;
            delete();
            MapperRoom m= new MapperRoom(s);
            this.setVisible(false);
        }
        if (e.getSource()==move){
            MapperRoom m= equipment.mapperRoom;
            m.getMoveEquip(equipment.id+"",
                    true,
                    equipment.name);
            m.setVisible(true);
            this.setVisible(false);
        }
    }
}
