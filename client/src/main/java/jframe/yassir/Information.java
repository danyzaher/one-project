package jframe.yassir;

import javax.swing.*;

public class Information extends JFrame {

    public Information() {
        this.setTitle("OneBuilding");
        this.setSize(900, 300);
        this.setLocationRelativeTo(null);
        JButton modif = new JButton("Modifier");

        JPanel fenetre = new JPanel();

        JLabel niveau1 = new JLabel("Le niveau d'accès 1 : la possibilté d'accéder au batiemnt , aux salles réserves munis de l'électricité et d'une connexion Internet dans tout le batiment. "
        );
        JLabel niveau2 = new JLabel("Le niveau d'accès 2 : toutes les possibilités du niveau d'accès 1 en plus d'une connexion Ethernet,et aux capteurs... ");
        JLabel niveau3 = new JLabel("Le niveau d'accès 3 : accès total aux salles et leurs fonctionnalités.");
        fenetre.add(niveau1);
        fenetre.add(niveau2);
        fenetre.add(niveau3);
        fenetre.add(modif);


        this.getContentPane().add(fenetre);

    }

    public static void main(String[] args) {
        Information  info = new Information();
        info.setVisible(true);
        }




    }

