package JFrame.Maxime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MapperSalle extends JFrame implements ActionListener {
    JFrame frame;
    int numeroSalle;
    ArrayList<Equippement> listEquippementDansLaSalle = new ArrayList<>();
    ArrayList<Equippement> listEquippementDisponible = new ArrayList<>();
    ArrayList<Emplacement> emplacementArrayList = new ArrayList<>();
    public MapperSalle(int numeroSalle){
        setTitle("salle numéro "+ numeroSalle);
        setVisible(true);
        setSize(400,300);
        Equippement equippement1 = new Equippement("equipement 1");
        equippement1.setBounds(50,100,20,15);
        add(equippement1);
        Equippement equippement2 = new Equippement("equipement 2");
        equippement2.setBounds(100,100,20,15);
        add(equippement2);
        setLayout(null);
        JMenu jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        JMenuItem e1 = new JMenuItem("liste des équipement à ajouter");
        e1.addActionListener(this);
        JMenuItem electrochroma = new JMenuItem("option électrochromatique");
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
        FenetreListEquipement fenetreListEquipement = new FenetreListEquipement(this);
    }
}
