package jframe.Dolf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SensorsP extends JFrame implements ActionListener {
    public SensorsP(){
        super();
        setTitle("nombre de capteurs present");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object[][] donnees = {
                {"5", "2", "2"},
                {"6", "3", "1"},
                {"7", "4  ", "2"},
                {"8", "1", "1"},
                {"9", "8", "3"},

        };

        String[] entetes = {"Ajouter", "retiré", "restant"};
        JPanel tab = new JPanel();

        JTable tableau = new JTable(donnees, entetes);

        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

        JPanel boutons=new JPanel();

        boutons.add(new JButton("Précedent"));

        getContentPane().add(boutons, BorderLayout.SOUTH);

        pack();

    }
    public static void main(String[] args) {
        new SensorsP().setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
