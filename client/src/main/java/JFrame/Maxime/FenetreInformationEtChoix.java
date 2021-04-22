package JFrame.Maxime;

import JFrame.julien.ElectroChromaManu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreInformationEtChoix extends JFrame implements ActionListener {

    Equipement equipement;
    JButton retirer;
    JButton déplacer;
    JButton information;
    public FenetreInformationEtChoix(Equipement equipement){
        this.equipement = equipement;
        setTitle(equipement.name);
        setVisible(true);
        setSize(500,600);
        setLayout(new FlowLayout());
        retirer = new JButton("retirer l'equipement");
        retirer.setBounds(50,100,30,50);
        add(retirer);
        déplacer = new JButton("deplacer l'equipement");
        déplacer.setBounds(50,200,30,50);
        add(déplacer);
        information = new JButton("information à propos de l'equipement");
        information.setBounds(50,300,30,50);
        add(information);
        information.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==information){
            ElectroChromaManu electroChromaManu = new ElectroChromaManu(""+equipement.id);
        }
    }
}
