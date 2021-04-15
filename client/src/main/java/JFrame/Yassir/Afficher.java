package JFrame.Yassir;
import javax.swing.*;
import java.awt.*;

public class Afficher extends JFrame {
    public Afficher() {
        super();

        setTitle("OneBuilding");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object[][] donnees = {
                {"A12A", "Paul", "2","Actif"},
                {"A13A", "Robert", "1", "Actif"},
                {"A14B", "Bob", "2", "Actif"},
                {"A15C", "Anna", "1", "Actif"},
                {"B251", "Sara", "3", "Actif"},

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
        new Afficher().setVisible(true);

    }
}