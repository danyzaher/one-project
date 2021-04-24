package JFrame.Maxime;

import JFrame.julien.OptionChroma;
import Socket.CCSocketTCPbis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MapperRoom extends JFrame implements ActionListener {
    public static Logger logger = LoggerFactory.getLogger("MapperSalle");
    String type;
    String nomSalle;
    JMenuItem e1;
    JMenuItem electrochroma;
    ArrayList<Equipement> listEquipementDansLaSalle = new ArrayList<>();
    ArrayList<Sensor> sensorArrayList = new ArrayList<>();
    ArrayList<Equipement> listEquipementAvailable = new ArrayList<>();
    ArrayList<Place> placeArrayList = new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();
    public MapperRoom(String nomSalle){
        this.nomSalle=nomSalle;
        setTitle(nomSalle);
        setVisible(true);
        setSize(400,300);
        getEquipement();
        setLayout(null);
        JMenu jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        e1 = new JMenuItem("Liste des équipement à ajouter");
        e1.addActionListener(this);
       // electrochroma = new JMenuItem("Option électrochromatique");
       // electrochroma.addActionListener(this);
       // jMenu.add(electrochroma);
        jMenu.add(e1);
        this.setJMenuBar(jMenuBar);
    }
    public void getEquipement(){
        logger.info("begin getEquipement");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("equipement");
        stringArrayList.add("dansSalle");
        stringArrayList.add(nomSalle);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        for(int k =0; k<result.size()-4;k=k+4){
            int id = Integer.parseInt(result.get(k));
            String nom = result.get(k+1);
            int position_x = Integer.parseInt(result.get(k+2));
            int position_y = Integer.parseInt(result.get(k+3));
            Equipement equipement = new Equipement(nom,position_x,position_y,id,nomSalle);
            listEquipementDansLaSalle.add(equipement);
        }
        for(int j=0;j<listEquipementDansLaSalle.size();j++){
            add(listEquipementDansLaSalle.get(j));
            if(listEquipementDansLaSalle.get(j).etat){
                listEquipementDansLaSalle.get(j).setBackground(Color.GREEN);
            }else{listEquipementDansLaSalle.get(j).setBackground(Color.RED);}
            if(listEquipementDansLaSalle.get(j).name.equals("fenêtre électrochromatique")){
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
        stringArrayList.add(nomSalle);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        for(int k =0; k<result.size()-4;k=k+4){
            int id = Integer.parseInt(result.get(k));
            String nom = result.get(k+1);
            int position_x = Integer.parseInt(result.get(k+2));
            int position_y = Integer.parseInt(result.get(k+3));
            Sensor sensor = new Sensor(nom,position_x,position_y,id,nomSalle);
            sensorArrayList.add(sensor);
        }
        for(int j=0;j<sensorArrayList.size();j++){
            add(sensorArrayList.get(j));
            if(sensorArrayList.get(j).etat){
                sensorArrayList.get(j).setBackground(Color.CYAN);
            }else{sensorArrayList.get(j).setBackground(Color.PINK);}
        }
    }
    public void ajouterLesEquippement(){ }
    public static void main(String[] args){
        new MapperRoom("salle numéro 3");
    }
    public void getEmplacement(String type){
        logger.info("begin getEmplacement");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("emplacement");
        stringArrayList.add(nomSalle);
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
        stringArrayList.add(p.id+"");
        stringArrayList.add(type);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.setVisible(false);
        MapperRoom mapperRoom = new MapperRoom(nomSalle);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==e1){
        WindowListEquipement windowListEquipement = new WindowListEquipement(this);}
        if (e.getSource()==electrochroma){
            OptionChroma optionChroma = new OptionChroma(nomSalle);
        }
    }
}
