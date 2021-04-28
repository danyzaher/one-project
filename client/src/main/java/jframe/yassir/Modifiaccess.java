package jframe.yassir;


import javax.swing.* ;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modifiaccess extends JFrame   {
    JButton confirm;
    JComboBox card;
    JComboBox access;
    public Modifiaccess () {
        this.setTitle("OneBuilding");
        this.setSize(600, 200);
        this.setLocationRelativeTo(null);
        JPanel pan1 = new JPanel();
        pan1.setLayout(new BoxLayout(pan1, BoxLayout.LINE_AXIS));
        String s[] = {"A01A", "A02A", "A01B"};

        card = new JComboBox(s);
        card.setPreferredSize(new Dimension(30,10));

        String s1[] = {"niveau 1", "niveau 2", "niveau 3"};

        access = new JComboBox(s1);
        access.setPreferredSize(new Dimension(30,10));

        pan1.add(card);
        pan1.add(access);
        JPanel pan2 = new JPanel();
        pan2.setLayout(new BoxLayout(pan2, BoxLayout.PAGE_AXIS));
        confirm = new JButton("Confirmer");
        pan2.add(pan1);
        pan2.add(confirm);

        this.getContentPane().add(pan2);
        this.setVisible(true);

    }
    public static void main(String[]args){

        Modifiaccess  modifacce = new Modifiaccess() ;
    }



}



