package JFrame.Maxime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FenetreListEquipement extends JFrame implements ActionListener, ItemListener {
    MapperSalle mapperSalle;

    public FenetreListEquipement(MapperSalle mapperSalle){
        this.mapperSalle=mapperSalle;
        setTitle("liste des équipement disponible pour la salle " + mapperSalle.numeroSalle);
        setVisible(true);
        setSize(1000,1000);
        JComboBox combobox;
        String s1[] = { "equipement 34", "equipement 45","equipement 22" };

        combobox = new JComboBox(s1);
        combobox.addItemListener(this);
        JLabel l1 = new JLabel("ajouter un equipement : ");
        JLabel l2 = new JLabel("equipement 34");

        l2.setForeground(Color.blue);

        JPanel p = new JPanel();
        p.add(l1);
        p.add(combobox);
        p.add(l2);
        add(p);
        JButton jButton = new JButton("Confirmer");
        add(jButton);
        jButton.setBounds(200,100,50,50);
        jButton.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
