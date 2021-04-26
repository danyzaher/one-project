package jframe.maxime.window;

import jframe.maxime.button.GoBackButton;
import jframe.maxime.button.GoBackMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class WindowListSensor extends JFrame implements ActionListener, ItemListener {
    public static Logger logger = LoggerFactory.getLogger("WindowListEquipement");
    MapperRoom mapperRoom;
    JLabel l1, l2;
    JButton jButton;
    JComboBox combobox;
    ArrayList<String> s1 = new ArrayList<>() ;
    ArrayList<String> result = new ArrayList<>();
    GoBackButton goBackButton;
    public WindowListSensor(MapperRoom mapperRoom){

        this.mapperRoom = mapperRoom;
        setTitle("liste des capteurs disponible pour la salle " + mapperRoom.nameRoom);
        setVisible(true);
        setLayout(new FlowLayout());
        setSize(1000,1000);
        getListSensorAvailable();
        String s2[] = s1.toArray(new String[0]);
        combobox = new JComboBox(s2);
        combobox.addItemListener(this);

        l1 = new JLabel("choisissez un capteur à ajouter ");
        l2 = new JLabel();

        l2.setForeground(Color.blue);

        JPanel p = new JPanel();
        p.add(l1);
        p.add(combobox);
        p.add(l2);
        add(p);
        jButton = new JButton("Confirmer");
        add(jButton);
        jButton.setBounds(200,100,100,100);
        jButton.addActionListener(this);
    }
    public void getListSensorAvailable(){
        logger.info("begin getListEquipementAvailable");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("sensor");
        stringArrayList.add("available");
        stringArrayList.add(mapperRoom.nameRoom);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        s1 = ccSocketTCP2.result;
        logger.info("s1= "+s1.toString());

    }
    public void newGoBack(JFrame j){
        goBackButton = new GoBackButton(this,j);
        add(goBackButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jButton){
            MapperRoom mapperRoom2 = new MapperRoom(mapperRoom.nameRoom);
            mapperRoom2.getEmplacement((String) combobox.getSelectedItem(),false);
        }
    }

    public void itemStateChanged(ItemEvent e)
    {
        // si l'état du combobox est modifiée
        if (e.getSource() == combobox) {

            l2.setText(" ["+combobox.getSelectedItem()+"]");
        }
    }
}
