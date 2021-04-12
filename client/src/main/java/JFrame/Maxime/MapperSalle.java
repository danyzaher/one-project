package JFrame.Maxime;

import javax.swing.*;
import java.util.ArrayList;

public class MapperSalle extends JFrame {
    JFrame frame;
    int numeroSalle;
    ArrayList<Equippement> listEquippementDansLaSalle = new ArrayList<>();
    ArrayList<Equippement> listEquippementDisponible = new ArrayList<>();
    public MapperSalle(int numeroSalle){
        setTitle("salle numéro "+ numeroSalle);
        setVisible(true);
        setSize(400,300);
        JButton button = new JButton("equip 1");
        button.setBounds(100,100,11,15);
        add(button);
        JButton button2 = new JButton("equip 2");
        button2.setBounds(50,50,11,15);
        add(button2);
        setLayout(null);
        JMenu jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        JMenuItem e1 = new JMenuItem("liste des équipement à ajouter");
        JMenuItem electrochroma = new JMenuItem("option électrochromatique");
        jMenu.add(electrochroma);
        jMenu.add(e1);
        this.setJMenuBar(jMenuBar);
    }
    public void ajouterLesEquippement(){
        for(int i=0; i<listEquippementDansLaSalle.size();i++){
            Equippement equippement = listEquippementDansLaSalle.get(i);
            JButton button = new JButton(equippement.nom);
            button.setBounds(equippement.positionX,equippement.positionY,11,15);
            add(button);}
    }
    public void remplirListEquippementDansLaSalle(){}
    public void remplirlistEquippementDisponible(){}
    public static void main(String[] args){
        new MapperSalle(3);
    }

}
