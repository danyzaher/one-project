package jframe.maxime.window;

import jframe.maxime.button.GoBackButton;
import socket.CCSocketTCPbis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class WindowListEquipement extends JFrame implements ActionListener, ItemListener {
    public static Logger logger = LoggerFactory.getLogger("WindowListEquipement");
    MapperRoom mapperRoom;
    JLabel l1, l2;
    JButton jButton;
    JComboBox combobox;
    ArrayList<String> s1 = new ArrayList<>() ;
    GoBackButton goBackButton;
    public WindowListEquipement(MapperRoom mapperRoom){

        this.mapperRoom = mapperRoom;
        setTitle("liste des équipement disponible pour la salle " + mapperRoom.nameRoom);
        setVisible(true);
        setLayout(new FlowLayout());
        setSize(700,700);
        getListEquipementAvailable();
        String[] s2 = s1.toArray(new String[0]);
        combobox = new JComboBox(s2);
        combobox.addItemListener(this);

        l1 = new JLabel("choisissez un équipement à ajouter ");
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
    public void getListEquipementAvailable(){
        logger.info("begin getListEquipementAvailable");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("equipment");
        stringArrayList.add("available");
        stringArrayList.add(mapperRoom.nameRoom);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        s1 = ccSocketTCP2.result;

    }
    public void newGoBack(JFrame j){
        goBackButton = new GoBackButton(this,j);
        add(goBackButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jButton){
            MapperRoom mapperRoom2 = new MapperRoom(mapperRoom.nameRoom);
            mapperRoom2.getEmplacement((String) combobox.getSelectedItem(), true);
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
