package jframe.maxime.window;

import jframe.maxime.button.*;
import jframe.julien.ElectroChromaAuto;
import socket.CCSocketTCPbis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class MapperRoom extends JFrame implements ActionListener {
    public static Logger logger = LoggerFactory.getLogger("MapperSalle");
    String type;
    boolean sensorOrequipement;
    String nameRoom;
    JMenuItem e1;
    JMenuItem e2;
    JMenuItem electrochroma;
    ArrayList<Equipement> listEquipementDansLaSalle = new ArrayList<>();
    ArrayList<Sensor> sensorArrayList = new ArrayList<>();
    ArrayList<Place> placeArrayList = new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();
    public MapperRoom(String nameRoom){
        this.nameRoom = nameRoom;
        setTitle(nameRoom);
        setVisible(true);
        setSize(400,300);
        getEquipement();
        getSensor();
        setLayout(null);
        JMenu jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        e1 = new JMenuItem("Liste des équipement à ajouter");
        e1.addActionListener(this);
        e2 = new JMenuItem("Liste des capteurs à ajouter");
        e2.addActionListener(this);
       // electrochroma = new JMenuItem("Option électrochromatique");
       // electrochroma.addActionListener(this);
       // jMenu.add(electrochroma);
        jMenu.add(e1);
        jMenu.add(e2);
        this.setJMenuBar(jMenuBar);
    }
    public void getEquipement(){
        logger.info("begin getEquipement");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("equipement");
        stringArrayList.add("dansSalle");
        stringArrayList.add(nameRoom);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        for(int k =0; k<result.size()-4;k=k+4){
            int id = Integer.parseInt(result.get(k));
            String nom = result.get(k+1);
            int position_x = Integer.parseInt(result.get(k+2));
            int position_y = Integer.parseInt(result.get(k+3));
            Equipement equipement = new Equipement(nom,position_x,position_y,id, nameRoom);
            listEquipementDansLaSalle.add(equipement);
        }
        for(int j=0;j<listEquipementDansLaSalle.size();j++){
            add(listEquipementDansLaSalle.get(j));
            if(listEquipementDansLaSalle.get(j).etat){
                listEquipementDansLaSalle.get(j).setBackground(Color.GREEN);
            }else{listEquipementDansLaSalle.get(j).setBackground(Color.RED);}
            if((listEquipementDansLaSalle.get(j).name.equals("fenêtre électrochromatique"))
            | (listEquipementDansLaSalle.get(j).name.equals("Store"))){
                logger.info("coloré en gris");
                listEquipementDansLaSalle.get(j).setBackground(Color.GRAY);
            }
        }

    }
    public void getSensor(){
        logger.info("begin getSensor");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("sensor");
        stringArrayList.add("dansSalle");
        stringArrayList.add(nameRoom);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        for(int k =0; k<result.size()-4;k=k+4){
            int id = Integer.parseInt(result.get(k));
            String nom = result.get(k+1);
            int position_x = Integer.parseInt(result.get(k+2));
            int position_y = Integer.parseInt(result.get(k+3));
            Sensor sensor = new Sensor(nom,position_x,position_y,id, nameRoom);
            sensorArrayList.add(sensor);
        }
        for(int j=0;j<sensorArrayList.size();j++){
            add(sensorArrayList.get(j));
            if(sensorArrayList.get(j).etat){
                sensorArrayList.get(j).setBackground(Color.CYAN);
            }else{sensorArrayList.get(j).setBackground(Color.PINK);}
        }
    }

    public static void main(String[] args){
        new MapperRoom("salle numéro 3");
    }
    public void getEmplacement(String type, boolean b){
        logger.info("begin getEmplacement");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("emplacement");
        if(b){
            stringArrayList.add("equipement");
            sensorOrequipement = true;
        } else {stringArrayList.add("sensor");
        sensorOrequipement = false;}
        stringArrayList.add(nameRoom);
        stringArrayList.add(type);
        this.type=type;
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        for (int k=0;k<result.size()-3;k=k+3){
            int id = Integer.parseInt(result.get(k));
            int position_x = Integer.parseInt(result.get(k+1));
            int position_y = Integer.parseInt(result.get(k+2));
            Place place = new Place(position_x,position_y,id,this);
            placeArrayList.add(place);
        }
        for(int k=0;k<placeArrayList.size();k++){
            add(placeArrayList.get(k));
            placeArrayList.get(k).setBounds(placeArrayList.get(k).x,placeArrayList.get(k).y,10,10);
            placeArrayList.get(k).setBackground(Color.ORANGE);
        }
    }
    public void setEquipement(Place p){
        logger.info("begin setEquipement");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("insert");
        stringArrayList.add("be_present");
        if (sensorOrequipement){
            stringArrayList.add("equipement");
        } else {stringArrayList.add("sensor");}
        stringArrayList.add(p.id+"");
        stringArrayList.add(type);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.setVisible(false);
        MapperRoom mapperRoom = new MapperRoom(nameRoom);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==e1){
        WindowListEquipement windowListEquipement = new WindowListEquipement(this);}
        if(e.getSource()==e2){
            WindowListSensor windowListSensor = new WindowListSensor(this);
        }
        if (e.getSource()==electrochroma){
            ElectroChromaAuto electroChromaAuto = new ElectroChromaAuto(nameRoom);
        }
    }
}
