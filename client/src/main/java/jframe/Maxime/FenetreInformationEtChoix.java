package jframe.Maxime;

import javax.swing.*;

public class FenetreInformationEtChoix extends JFrame {
    Equippement equippement;
    public FenetreInformationEtChoix(Equippement equippement){
        this.equippement = equippement;
        setTitle(equippement.nom);
        setVisible(true);
        setSize(500,600);
        JButton retirer = new JButton("retirer l'equipement");
        retirer.setBounds(50,100,30,50);
        add(retirer);
        JButton déplacer = new JButton("deplacer l'equipement");
        déplacer.setBounds(50,200,30,50);
        add(déplacer);
        JButton information = new JButton("information à propos de l'equipement");
        information.setBounds(50,300,30,50);
        add(information);
        setLayout(null);
    }
}
