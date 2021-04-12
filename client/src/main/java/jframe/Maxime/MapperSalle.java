package jframe.Maxime;

import javax.swing.*;
import java.util.ArrayList;

public class MapperSalle extends JFrame {
    JFrame frame;
    int numeroSalle;
    ArrayList<Equippement> listEquippementDansLaSalle = new ArrayList<>();
    ArrayList<Equippement> listEquippementDisponible = new ArrayList<>();
    public MapperSalle(){
        setTitle("salle numéro 3");
        setVisible(true);
        setSize(400,300);
        JButton button = new JButton("equip 1");
        button.setBounds(100,100,11,15);
        add(button);
        JButton button2 = new JButton("equip 2");
        button2.setBounds(50,50,11,15);
        add(button2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JMenu jMenu = new JMenu("Menu");
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        JMenuItem e1 = new JMenuItem("liste des équipement à ajouter");
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
        new MapperSalle();
    }

}
