package JFrame.Maxime;

import JFrame.julien.ElectroChromaManu;
import Socket.CCSocketTCPbis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FenetreInformationEtChoix extends JFrame implements ActionListener {
    public static Logger logger = LoggerFactory.getLogger("FenetreInformationEtChoix");
    Equipement equipement;
    JButton delete;
    JButton move;
    JButton information;
    public FenetreInformationEtChoix(Equipement equipement){
        this.equipement = equipement;
        setTitle(equipement.name);
        setVisible(true);
        setSize(500,600);
        setLayout(new FlowLayout());
        delete = new JButton("Retirer l'equipement");
        delete.setBounds(50,100,30,50);
        add(delete);
        delete.addActionListener(this);
        move = new JButton("Deplacer l'equipement");
        move.setBounds(50,200,30,50);
        add(move);
        if(equipement.name.equals("Fenêtre électrochromatique")){
        information = new JButton("Information à propos de l'equipement");
        information.setBounds(50,300,30,50);
        add(information);
        information.addActionListener(this);}

    }
    public void delete(){
        logger.info("begin getEquipement");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("delete");
        stringArrayList.add("be_present");
        stringArrayList.add(equipement.id+"");
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==information){
            ElectroChromaManu electroChromaManu = new ElectroChromaManu(""+equipement.id);
        }
        if (e.getSource()==delete){
            String s = equipement.nomSalle;
            delete();
            MapperRoom m= new MapperRoom(s);
            this.setVisible(false);
        }
    }
}
