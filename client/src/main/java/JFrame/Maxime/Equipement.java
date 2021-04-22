package JFrame.Maxime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Equipement extends JButton implements ActionListener {
    String nom;
    final int WIDTH=15;
    final int HEIGHT=20;
    boolean etat = true;
    int id;
    public Equipement(String nom, int x,int y,int id){
        this.id=id;
        this.nom = nom;
        addActionListener(this);
        setBounds(x,y,WIDTH,HEIGHT);
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
