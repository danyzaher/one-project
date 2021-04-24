package jframe.yassir;

import javax.swing.*;

public class Show extends JFrame {
    JButton prec;

    public Show() {
        this.setTitle("OneBuilding");
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel num = new JPanel();
        num.add(new JLabel("Numéro de badge:"));
        num.add(new JLabel("A12A"));


        JPanel num1 = new JPanel();
        num1.add(new JLabel("Propriétaire du badge:"));
        num1.add(new JLabel("Paul Denis"));


        JPanel num2 = new JPanel();
        num2.add(new JLabel("Niveau d'accès:"));
        num2.add(new JLabel("2"));


        JPanel num3 = new JPanel();
        num3.add(new JLabel("Statut:"));
        num3.add(new JLabel("Actif"));

        JPanel num4 = new JPanel();
        num4.setLayout(new BoxLayout(num4, BoxLayout.PAGE_AXIS));
        prec=new JButton("Précedent");

        num4.add(prec);





        JPanel fenetre = new JPanel();
        fenetre.setLayout(new BoxLayout(fenetre, BoxLayout.PAGE_AXIS));
        fenetre.add(num);
        fenetre.add(num1);
        fenetre.add(num2);
        fenetre.add(num3);
        fenetre.add(num4);


        this.getContentPane().add(fenetre);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Show affiche = new Show();
    }
}
