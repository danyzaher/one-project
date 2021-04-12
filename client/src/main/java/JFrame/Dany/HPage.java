package JFrame.Dany;

import JFrame.Maxime.HPageEntreprise;
import JFrame.Maxime.MapperSalle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HPage extends JFrame implements ItemListener, ActionListener{

    JComboBox combobox;
    JLabel l1, l2;
    public HPage(){
        setTitle("page d'accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 200);
        setVisible(true);

        String s1[] = { "Fritel Inc", "FloraFlore" };

        combobox = new JComboBox(s1);
        combobox.addItemListener(this);

        l1 = new JLabel("choisissez une société ");
        l2 = new JLabel("[Fritel Inc]");

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
    public static void main(String[] args){
         HPage h = new HPage();
    }
    public void itemStateChanged(ItemEvent e)
    {
        // si l'état du combobox est modifiée
        if (e.getSource() == combobox) {

            l2.setText(" ["+combobox.getSelectedItem()+"]");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        HPageEntreprise hPageEntreprise = new HPageEntreprise();
    }
}
