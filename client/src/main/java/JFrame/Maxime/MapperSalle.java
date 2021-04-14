package JFrame.Maxime;

import JFrame.julien.OptionChroma;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MapperSalle extends JFrame implements ActionListener {
    public static Logger logger = LoggerFactory.getLogger("MapperSalle");
    JFrame frame;
    int numeroSalle;
    JMenuItem e1;
    JMenuItem electrochroma;
    ArrayList<Equippement> listEquippementDansLaSalle = new ArrayList<>();
    ArrayList<Equippement> listEquippementDisponible = new ArrayList<>();
    ArrayList<Emplacement> emplacementArrayList = new ArrayList<>();
    public MapperSalle(int numeroSalle){
        this.numeroSalle = numeroSalle;
        setTitle("salle numéro "+ numeroSalle);
        setVisible(true);
        setSize(400,300);
        Equippement equippement1 = new Equippement("equipement 1");
        equippement1.setBounds(50,100,20,15);
        equippement1.setBackground(Color.GREEN);
        add(equippement1);
        Equippement equippement2 = new Equippement("equipement 2");
        equippement2.setBounds(100,100,20,15);
        add(equippement2);
        equippement2.setBackground(Color.RED);
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
    public void ajouterLesEquippement(){
        for(int i=0; i<listEquippementDansLaSalle.size();i++){
            Equippement equippement = listEquippementDansLaSalle.get(i);
            JButton button = new JButton(equippement.nom);
            add(button);}
    }
    public void remplirListEquippementDansLaSalle(){}
    public void remplirlistEquippementDisponible(){}
    public static void main(String[] args){
        new MapperSalle(3);
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
