package jframe.maxime.window;

import jframe.maxime.button.GoBackButton;
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
    GoBackButton goBackButton;
    public WindowSensor(Sensor sensor){

        this.sensor = sensor;
        setTitle(sensor.name+" "+sensor.id);
        setVisible(true);
        setSize(500,600);
        setLayout(new FlowLayout());
        delete = new JButton("Retirer le capteur");
        delete.setBounds(50,100,30,50);
        add(delete);
        delete.addActionListener(this);
        move = new JButton("Déplacer le capteur");
        move.setBounds(50,200,30,50);
        add(move);
        move.addActionListener(this);
        JLabel jLabel= new JLabel("position en x du capteur = " +sensor.x );
        JLabel jLabel2 = new JLabel("position en y du capteur = "+sensor.y);
        add(jLabel);
        add(jLabel2);

    }
    public void delete(){
        logger.info("begin delete sensor");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("delete");
        stringArrayList.add("be_present");
        stringArrayList.add("sensor");
        stringArrayList.add(sensor.id+"");
        new CCSocketTCPbis(stringArrayList);
    }
    public void newGoBack(JFrame j){
        goBackButton = new GoBackButton(this,j);
        add(goBackButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==delete){
            String s = sensor.roomName;
            delete();
            MapperRoom m= new MapperRoom(s);
            this.setVisible(false);
        }
        if (e.getSource()==move){
            MapperRoom m= sensor.mapperRoom;
            m.getMoveEquip(sensor.id+"",
                    false,
                    sensor.name);
            m.setVisible(true);
            this.setVisible(false);
        }
    }
}
