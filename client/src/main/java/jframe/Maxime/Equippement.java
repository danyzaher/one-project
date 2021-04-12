package jframe.Maxime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Equippement implements ActionListener {
    JButton button;
    String nom;
    int positionX;
    int positionY;
    boolean etat = true;
    public Equippement(){}

    @Override
    public void actionPerformed(ActionEvent e) {
        new FenetreInformationEtChoix(this);
    }
}
