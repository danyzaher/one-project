package JFrame.Maxime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FenetreListEquipement extends JFrame implements ActionListener, ItemListener {
    MapperSalle mapperSalle;
    JLabel l1, l2;
    JComboBox combobox;
    public FenetreListEquipement(MapperSalle mapperSalle){
        this.mapperSalle=mapperSalle;
        setTitle("liste des équipement disponible pour la salle " + mapperSalle.numeroSalle);
        setVisible(true);
        setLayout(new FlowLayout());
        setSize(1000,1000);
        String s1[] = { "équipement 21", "équipement 34", "équipement 43" };

        combobox = new JComboBox(s1);
        combobox.addItemListener(this);

        l1 = new JLabel("choisissez un équipement à ajouter ");
        l2 = new JLabel("équipement 21");

        l2.setForeground(Color.blue);

        JPanel p = new JPanel();
        p.add(l1);
        p.add(combobox);
        p.add(l2);
        add(p);
        JButton jButton = new JButton("Confirmer");
        add(jButton);
        jButton.setBounds(200,100,100,100);
        jButton.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
