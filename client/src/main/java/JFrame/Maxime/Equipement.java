package JFrame.Maxime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Equipement extends JButton implements ActionListener {
    String name;
    final int WIDTH=15;
    final int HEIGHT=20;
    boolean etat = true;
    int id;
    public Equipement(String name, int x,int y,int id){
        this.id=id;
        this.name = name;
        addActionListener(this);
        setBounds(x,y,WIDTH,HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FenetreInformationEtChoix fenetreInformationEtChoix =new FenetreInformationEtChoix(this);
    }
}
