package jframe.Maxime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Equippement extends JButton implements ActionListener {
    String nom;
    boolean etat = true;
    public Equippement(String nom){
        this.nom = nom;
        addActionListener(this);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isEtat() {
        return etat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FenetreInformationEtChoix fenetreInformationEtChoix =new FenetreInformationEtChoix(this);
    }
}
