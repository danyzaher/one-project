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

public class MapperRoom extends JFrame implements ActionListener {
    public static Logger logger = LoggerFactory.getLogger("MapperRoom");
    String type;
    boolean sensorOrequipement;
    String nameRoom;
    JMenuItem e1;
    JMenuItem e2;
    JPanel center = new JPanel();
    JMenuItem electrochroma;
    ArrayList<Equipement> listEquipementDansLaSalle = new ArrayList<>();
    ArrayList<Sensor> sensorArrayList = new ArrayList<>();
    ArrayList<Place> placeArrayList = new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();
    JMenu jMenu;
    GoBackMenu goBackMenu;
    int width;
    int height;
    Legende legende = new Legende();
    public MapperRoom(String nameRoom){
        this.nameRoom = nameRoom;
        setTitle(nameRoom);
        setVisible(true);
        getSizeBis();
        setSize(800,800);
        getEquipement();
        getSensor();
        setLayout(null);
        jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        jMenuBar.add(legende);
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
            Equipement equipement = new Equipement(nom,position_x,position_y,id, nameRoom,this);
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
            Sensor sensor = new Sensor(nom,position_x,position_y,id, nameRoom,this);
            sensorArrayList.add(sensor);
        }
        for(int j=0;j<sensorArrayList.size();j++){
            Component add = center.add(sensorArrayList.get(j));
            add(add);
            if(sensorArrayList.get(j).etat){
                sensorArrayList.get(j).setBackground(Color.CYAN);
            }else{sensorArrayList.get(j).setBackground(Color.PINK);}
        }
    }
    public void newGoBack(JFrame j){
        goBackMenu = new GoBackMenu(this,j);
        jMenu.add(goBackMenu);
    }
    public static void main(String[] args){
        new MapperRoom("A122");
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
            center.add(placeArrayList.get(k));
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
        MapperRoom mapperRoom = new MapperRoom(nameRoom);
        this.setVisible(false);
    }
    public void getSizeBis(){
        logger.info("begin getSize");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("size");
        stringArrayList.add(nameRoom);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        width = Integer.parseInt(result.get(0))*10;
        height = Integer.parseInt(result.get(1))*15;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==e1){
        WindowListEquipement windowListEquipement = new WindowListEquipement(this);
        windowListEquipement.newGoBack(this);}
        if(e.getSource()==e2){
            WindowListSensor windowListSensor = new WindowListSensor(this);
            windowListSensor.newGoBack(this);
        }
        if (e.getSource()==electrochroma){
            ElectroChromaAuto electroChromaAuto = new ElectroChromaAuto(nameRoom);
        }
    }
}
