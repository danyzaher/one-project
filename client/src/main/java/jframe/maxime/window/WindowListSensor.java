package jframe.maxime.window;

import jframe.maxime.button.GoBackButton;
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
    public static Logger logger = LoggerFactory.getLogger("WindowListEquipment");
    MapperRoom mapperRoom;
    private JLabel l1, l2;
    private JButton jButton;
    private JComboBox jComboBox;
    private ArrayList<String> listSensor = new ArrayList<>() ;
    GoBackButton goBackButton;
    public WindowListSensor(MapperRoom mapperRoom){

        this.mapperRoom = mapperRoom;
        setTitle("Liste des capteurs disponibles pour la salle " + mapperRoom.nameRoom);
        setVisible(true);
        setLayout(new FlowLayout());
        setSize(700,700);
        getListSensorAvailable();
        String[] s2 = listSensor.toArray(new String[0]);
        jComboBox = new JComboBox(s2);
        jComboBox.addItemListener(this);

        l1 = new JLabel("Choisissez un capteur à ajouter ");
        l2 = new JLabel();

        l2.setForeground(Color.blue);

        JPanel p = new JPanel();
        p.add(l1);
        p.add(jComboBox);
        p.add(l2);
        add(p);
        jButton = new JButton("Confirmer");
        add(jButton);
        jButton.setBounds(200,100,100,100);
        jButton.addActionListener(this);
    }
    private void getListSensorAvailable(){
        logger.info("begin getListSensorAvailable");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("sensor");
        stringArrayList.add("available");
        stringArrayList.add(mapperRoom.nameRoom);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        listSensor = ccSocketTCP2.result;
        logger.info("s1= "+ listSensor.toString());

    }
    public void newGoBack(JFrame j){
        goBackButton = new GoBackButton(this,j);
        add(goBackButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jButton){
            MapperRoom mapperRoom2 = new MapperRoom(mapperRoom.nameRoom);
            mapperRoom2.getEmplacement((String) jComboBox.getSelectedItem(),false);
        }
    }

    public void itemStateChanged(ItemEvent e)
    {
        // If statement of the combobox is modified
        if (e.getSource() == jComboBox) {

            l2.setText(" ["+ jComboBox.getSelectedItem()+"]");
        }
    }
}
