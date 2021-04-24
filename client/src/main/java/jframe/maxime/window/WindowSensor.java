package jframe.maxime.window;

import jframe.maxime.button.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WindowSensor extends JFrame implements ActionListener {
    public static Logger logger = LoggerFactory.getLogger("WindowSensor");
    Sensor sensor;
    JButton delete;
    JButton move;
    public WindowSensor(Sensor sensor){
        this.sensor = sensor;
        setTitle(sensor.name);
        setVisible(true);
        setSize(500,600);
        setLayout(new FlowLayout());
        delete = new JButton("Retirer le capteur");
        delete.setBounds(50,100,30,50);
        add(delete);
        delete.addActionListener(this);
        move = new JButton("Deplacer le capteur");
        move.setBounds(50,200,30,50);
        add(move);

    }
    public void delete(){
        logger.info("begin getEquipement");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("delete");
        stringArrayList.add("be_present");
        stringArrayList.add("sensor");
        stringArrayList.add(sensor.id+"");
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==delete){
            String s = sensor.roomName;
            delete();
            MapperRoom m= new MapperRoom(s);
            this.setVisible(false);
        }
    }
}
