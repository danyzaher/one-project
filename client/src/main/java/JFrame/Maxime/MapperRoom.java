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
    JFrame frame;
    String nomSalle;
    JMenuItem e1;
    JMenuItem electrochroma;
    ArrayList<Equipement> listEquipementDansLaSalle = new ArrayList<>();
    ArrayList<Capteur> sensorArrayList = new ArrayList<>();
    ArrayList<Equipement> listEquipementAvailable = new ArrayList<>();
    ArrayList<Place> placeArrayList = new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();
    public MapperRoom(String nomSalle){
        this.nomSalle=nomSalle;
        setTitle(nomSalle);
        setVisible(true);
        setSize(400,300);
        getEquipement();
        Equipement equipement1 = new Equipement("equipement 1",100,100);
        listEquipementDansLaSalle.add(equipement1);
        equipement1.setBackground(Color.GREEN);
        add(equipement1);
        Equipement equipement2 = new Equipement("equipement 2",100,100);
        listEquipementDansLaSalle.add(equipement2);
        add(equipement2);
        equipement2.setBackground(Color.RED);
        Capteur capteur1 = new Capteur("capteur 1");
        sensorArrayList.add(capteur1);
        capteur1.setBounds(50,100,20,15);
        capteur1.setBackground(Color.GREEN);
        capteur1.setBorder(capteur1);
        add(capteur1);
        setLayout(null);
        JMenu jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        e1 = new JMenuItem("liste des équipement à ajouter");
        e1.addActionListener(this);
        electrochroma = new JMenuItem("option électrochromatique");
        electrochroma.addActionListener(this);
        jMenu.add(electrochroma);
        jMenu.add(e1);
        this.setJMenuBar(jMenuBar);
    }
    public void getEquipement(){
        logger.info("begin getEquipement");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("equipement");
        stringArrayList.add(nomSalle);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        for(int k =0; k<result.size()-3;k=k+3){
            String nom = result.get(k);
            int position_x = Integer.parseInt(result.get(k+1));
            int position_y = Integer.parseInt(result.get(k+2));
            Equipement equipement = new Equipement(nom,position_x,position_y);
            listEquipementDansLaSalle.add(equipement);
        }
        for(int j=0;j<listEquipementDansLaSalle.size();j++){
            add(listEquipementDansLaSalle.get(j));
        }
    }
    public void ajouterLesEquippement(){ }
    public void remplirListEquippementDansLaSalle(){}
    public void remplirlistEquippementDisponible(){}
    public static void main(String[] args){
        new MapperRoom("salle numéro 3");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==e1){
        FenetreListEquipement fenetreListEquipement = new FenetreListEquipement(this);}
        if (e.getSource()==electrochroma){
            OptionChroma optionChroma = new OptionChroma();
        }
    }
}
