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
    boolean sensorEquipment;
    String nameRoom;
    JMenuItem e1;
    JMenuItem e2;
    JMenuItem electrochroma;
    ArrayList<Equipment> listEquipmentInRoom = new ArrayList<>();
    ArrayList<Sensor> sensorArrayList = new ArrayList<>();
    ArrayList<Place> placeArrayList = new ArrayList<>();
    ArrayList<PlaceMove> placeMoveArrayList = new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();
    JMenu jMenu;
    GoBackMenu goBackMenu;
    GoBackMenuTotal goBackMenuTotal = new GoBackMenuTotal(this);
    int width;
    int height;
    Legend legend = new Legend();
    public MapperRoom(String nameRoom){
        this.nameRoom = nameRoom;
        setTitle(nameRoom);
        setVisible(true);
        getSizeBis();
        setResizable(false);
        setSize(width,height);
        getEquipment();
        getSensor();
        setLayout(null);
        jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        jMenuBar.add(legend);
        e1 = new JMenuItem("Liste des équipement à ajouter");
        e1.addActionListener(this);
        e2 = new JMenuItem("Liste des capteurs à ajouter");
        e2.addActionListener(this);
        jMenu.add(e1);
        jMenu.add(e2);
        jMenu.add(goBackMenuTotal);
        this.setJMenuBar(jMenuBar);
    }
    public void getEquipment(){
        logger.info("begin getEquipment");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("equipment");
        stringArrayList.add("inRoom");
        stringArrayList.add(nameRoom);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        for(int k =0; k<result.size()-4;k=k+4){
            int id = Integer.parseInt(result.get(k));
            String nom = result.get(k+1);
            int position_x = Integer.parseInt(result.get(k+2));
            int position_y = Integer.parseInt(result.get(k+3));
            Equipment equipment = new Equipment(nom,position_x,position_y,id, nameRoom,this);
            listEquipmentInRoom.add(equipment);
        }
        for (Equipment equipment : listEquipmentInRoom) {
            add(equipment);
            if (equipment.animated) {
                equipment.setBackground(Color.GREEN);
            } else {
                equipment.setBackground(Color.RED);
            }
            if ((equipment.name.equals("fenêtre électrochromatique"))
                    | (equipment.name.equals("Store"))) {
                logger.info("coloré en gris");
                equipment.setBackground(Color.GRAY);
            }
        }

    }
    public void getSensor(){
        logger.info("begin getSensor");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("sensor");
        stringArrayList.add("inRoom");
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
        for (Sensor sensor : sensorArrayList) {
            add(sensor);
            if (sensor.animated) sensor.setBackground(Color.CYAN);
            else sensor.setBackground(Color.PINK);
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
            stringArrayList.add("equipment");
            sensorEquipment = true;
        } else {stringArrayList.add("sensor");
        sensorEquipment = false;}
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
        for (Place place : placeArrayList) {
            add(place);
            place.setBounds(place.x, place.y, 10, 10);
            place.setBackground(Color.ORANGE);
        }
    }
    public void setEquipment(Place p){
        logger.info("begin setEquipment");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("insert");
        stringArrayList.add("be_present");
        if (sensorEquipment){
            stringArrayList.add("equipment");
        } else {stringArrayList.add("sensor");}
        stringArrayList.add(p.id+"");
        stringArrayList.add(type);
        new CCSocketTCPbis(stringArrayList);
        MapperRoom mapperRoom = new MapperRoom(nameRoom);
        this.setVisible(false);
    }
    public void setEquipment(PlaceMove p){
        logger.info("begin setEquipment");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("update");
        stringArrayList.add("be_present");
        if (sensorEquipment){
            stringArrayList.add("equipment");
        } else {stringArrayList.add("sensor");}
        stringArrayList.add(p.id2+""); //id place
        stringArrayList.add(p.id); //id equip or sensor
        new CCSocketTCPbis(stringArrayList);
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
        width = Integer.parseInt(result.get(0))*150;
        height = Integer.parseInt(result.get(1))*150;
    }
    public void getMoveEquip(String id, boolean b, String type){
        logger.info("begin getMoveEquip");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("emplacement");
        if(b){ stringArrayList.add("equipment");
            sensorEquipment = true;
        } else {stringArrayList.add("sensor");
            sensorEquipment = false;}
        stringArrayList.add(nameRoom);
        stringArrayList.add(type);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        for (int k=0;k<result.size()-3;k=k+3){
            int id2 = Integer.parseInt(result.get(k));
            int position_x = Integer.parseInt(result.get(k+1));
            int position_y = Integer.parseInt(result.get(k+2));
            PlaceMove place = new PlaceMove(position_x,position_y,id2,this,id);
            placeMoveArrayList.add(place);
        }
        for (PlaceMove placeMove : placeMoveArrayList) {
            add(placeMove);
            placeMove.setBounds(placeMove.x, placeMove.y, 10, 10);
            placeMove.setBackground(Color.ORANGE);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==e1){
        WindowListEquipment windowListEquipment = new WindowListEquipment(this);
        windowListEquipment.newGoBack(this);}
        if(e.getSource()==e2){
            WindowListSensor windowListSensor = new WindowListSensor(this);
            windowListSensor.newGoBack(this);
        }
        if (e.getSource()==electrochroma){
            ElectroChromaAuto electroChromaAuto = new ElectroChromaAuto(nameRoom);
        }
    }
}
