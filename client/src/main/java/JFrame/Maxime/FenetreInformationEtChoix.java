package JFrame.Maxime;

import javax.swing.*;

public class FenetreInformationEtChoix extends JFrame {
    Equippement equippement;
    public FenetreInformationEtChoix(Equippement equippement){
        this.equippement = equippement;
        setTitle(equippement.nom);
        setVisible(true);
        setSize(100,400);
        JButton retirer = new JButton("retirer l'equipement");
        retirer.setBounds(50,100,30,50);
        add(retirer);
        JButton déplacer = new JButton("deplacer l'equipement");
        déplacer.setBounds(50,200,30,50);
        add(déplacer);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    }
}
