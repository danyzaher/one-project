package JFrame.Maxime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Equippement extends JButton implements ActionListener {
    String nom;
    boolean etat = true;
    public Equippement(String nom){
        this.nom = nom;
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
        new FenetreInformationEtChoix(this);
    }
}
