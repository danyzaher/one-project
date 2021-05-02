package jframe;

import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class HPage extends JFrame implements ItemListener, ActionListener{
    JComboBox jComboBox;
    JLabel l1, l2;
    ArrayList<String> s1 = new ArrayList<>();
    public HPage(){
        setTitle("Page d'Accueil");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(800, 300);
        setResizable(false);
        setLayout(new FlowLayout());

        listCompany();
        String[] s2 = s1.toArray(new String[0]);
        jComboBox = new JComboBox(s2);
        jComboBox.addItemListener(this);

        l1 = new JLabel("Choisissez une société ");
        l2 = new JLabel(s1.get(0));

        l2.setForeground(Color.blue);

        JPanel p = new JPanel();
        p.add(l1);
        p.add(jComboBox);
        p.add(l2);
        add(p);
        JButton jButton = new JButton("CONFIRMER");
        add(jButton);
        jButton.setBounds(200,100,100,100);
        jButton.addActionListener(this);

        setVisible(true);

    }
    public static void main(String[] args){
         HPage h = new HPage();
    }
    public void listCompany(){
        // show Company name
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("company");
        stringArrayList.add("name");
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        s1 = ccSocketTCP2.result;


    }
    public void itemStateChanged(ItemEvent e)
    {
        // si l'état du combobox est modifiée
        if (e.getSource() == jComboBox) {

            l2.setText((String) jComboBox.getSelectedItem());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = l2.getText();
        HPageCompany hPageCompany = new HPageCompany(s);
        hPageCompany.newGoBack(this);
    }
}
