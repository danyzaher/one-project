package JFrame.Maxime;

import Socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Equipement extends JButton implements ActionListener {
    String name;
    final int WIDTH=15;
    final int HEIGHT=20;
    boolean etat;
    int id;
    ArrayList<String> result;
    public Equipement(String name, int x,int y,int id){
        this.id=id;
        this.name = name;
        addActionListener(this);
        getEtat();
        setBounds(x,y,WIDTH,HEIGHT);
    }
    public void getEtat() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("equipement");
        stringArrayList.add("etat");
        stringArrayList.add("" + id);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        etat = result.get(0).equals("t");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FenetreInformationEtChoix fenetreInformationEtChoix =new FenetreInformationEtChoix(this);
    }
}
