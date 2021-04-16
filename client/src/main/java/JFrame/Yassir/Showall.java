package JFrame.Yassir;
import javax.swing.*;
import java.awt.*;

public class Showall extends JFrame {
    public Showall() {
        super();

        setTitle("OneBuilding");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object[][] donnees = {
                {"A12A", "Paul Denis", "2","Actif"},
                {"A13A", "Robert Duval", "1", "Actif"},
                {"A14B", "Bob Hubert  ", "2", "Actif"},
                {"A15C", "Anna Sanchez", "1", "Actif"},
                {"B251", "Sara Moulin", "3", "Actif"},

        };

        String[] entetes = {"Numéro Badge", "Employé", "Niveau d'accès", "Statut"};

        JPanel tab = new JPanel();

        JTable tableau = new JTable(donnees, entetes);

        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

        JPanel boutons=new JPanel();

        boutons.add(new JButton("Précedent"));

        getContentPane().add(boutons, BorderLayout.SOUTH);









        pack();
    }

    public static void main(String[] args) {
        new Showall().setVisible(true);

    }
}