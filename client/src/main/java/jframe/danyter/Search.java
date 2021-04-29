package jframe.danyter;

import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Search  extends JFrame implements ActionListener {
    JPanel bigpan = new JPanel();
    JPanel pan1 = new JPanel();
    JPanel pan2 = new JPanel();
    JPanel pan3 = new JPanel();
    JPanel pan4 = new JPanel();
    JPanel pan5 = new JPanel();
    JPanel pan6 = new JPanel();
    JPanel pan7 = new JPanel();
    JLabel title = new JLabel("Entrez vos critères de recherche");
    JLabel lsun = new JLabel("Orientation du soleil importante");
    JLabel lfelectro = new JLabel("Fenetre electrochromatique           ");
    JLabel lheight = new JLabel("Hauteur importante                      ");
    JLabel lbmin = new JLabel("Budget min                   ");
    JLabel lbmax = new JLabel("Budget max                   ");
    JLabel lnbpeople = new JLabel("Nombre de personnes ");
    JTextField bmin = new JTextField();
    JTextField bmax = new JTextField();
    JTextField nbpeople = new JTextField();
    JCheckBox sun = new JCheckBox();
    JCheckBox electrofen = new JCheckBox();
    JCheckBox height = new JCheckBox();
    JButton done = new JButton("Rechercher");

    public Search() {
        setTitle("Recherche");
        setVisible(true);
        setSize(400,300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pan1.add(title);
        pan4.add(lsun);
        pan5.add(lfelectro);
        pan6.add(lheight);
        pan2.add(lbmin);
        pan3.add(lbmax);
        pan7.add(lnbpeople);
        pan2.add(bmin);
        pan3.add(bmax);
        pan7.add(nbpeople);
        pan4.add(sun);
        pan5.add(electrofen);
        pan6.add(height);

        bmin.setSize(30,30);
        pan2.setLayout(new BoxLayout(pan2,BoxLayout.LINE_AXIS));
        pan3.setLayout(new BoxLayout(pan3,BoxLayout.LINE_AXIS));
        pan4.setLayout(new BoxLayout(pan4,BoxLayout.LINE_AXIS));
        pan5.setLayout(new BoxLayout(pan5,BoxLayout.LINE_AXIS));
        pan6.setLayout(new BoxLayout(pan6,BoxLayout.LINE_AXIS));
        pan7.setLayout(new BoxLayout(pan7,BoxLayout.LINE_AXIS));
        bigpan.add(pan1);
        bigpan.add(pan2);
        bigpan.add(pan3);
        bigpan.add(pan7);
        bigpan.add(pan4);
        bigpan.add(pan5);
        bigpan.add(pan6);

        bigpan.add(done);
        bigpan.setLayout(new BoxLayout(bigpan,BoxLayout.PAGE_AXIS));
        this.add(bigpan);
        done.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == done) {
            System.out.println("Nombre employés : " + nbpeople.getText());
            System.out.println("Budget min : " + bmin.getText() + " Budget max : " + bmax.getText());
            System.out.println("Fenetres electrochromatiques : " + electrofen.isSelected());
            System.out.println("Soleil important : " + sun.isSelected());
            System.out.println("Hauteur importante : " + height.isSelected());

            // GET ALL ROOMS IN THE CLIENTS'S BUDGET

            String command = "getroominb(" + bmin.getText() + "," + bmax.getText() + "," + electrofen.isSelected() + ");";
            ArrayList<String> commands = new ArrayList<>();
            ArrayList<String> values;
            commands.add(command);
            socket.CCSocketTCPbis cc = new socket.CCSocketTCPbis(commands);
            commands.clear();
            // SET A GRADE FOR EACH IDs

            if (sun.isSelected() && height.isSelected()) {
                for (String value : cc.result) {
                    command = "setgradesunheight(" + value + ");";
                    commands.add(command);
                    new socket.CCSocketTCPbis(commands);
                    commands.clear();

                }
            }
            if (sun.isSelected() && !height.isSelected()) {
                for (String value : cc.result) {
                    command = "setgradesun(" + value + ");";
                    commands.add(command);
                    new socket.CCSocketTCPbis(commands);
                    commands.clear();

                }
            }
            if (!sun.isSelected() && height.isSelected()) {
                for (String value : cc.result) {
                    command = "setgradeheight(" + value + ");";
                    commands.add(command);
                    new socket.CCSocketTCPbis(commands);
                    commands.clear();
                }
            }
            if (!sun.isSelected() && !height.isSelected()) {
                for (String value : cc.result) {
                    command = "setgrade(" + value + ");";
                    commands.add(command);
                    new socket.CCSocketTCPbis(commands);
                    commands.clear();

                }
            }

            // GET IDs AND THE ROOM'S CAPACITY ORDERED BY THEIR GRADE - ASC BECAUSE THE STACK WILL REVERSE THE ORDER
            command = " room_s_number from room  where room_s_number in (select getroominb(" + bmin.getText() + "," + bmax.getText() + ")) order by grade asc;";
            String command1 = " capacity from room  where room_s_number in (select getroominb(" + bmin.getText() + "," + bmax.getText() + ")) order by grade asc;";
            commands.add(command);
            CCSocketTCPbis cc2 = new CCSocketTCPbis(commands);
            commands.clear();
            commands.add(command1);
            CCSocketTCPbis cc3 = new CCSocketTCPbis(commands);

            // USE STACK TO ONLY USE EACH IDs ONE TIME
            Stack<String> idroom = new Stack<>();
            Stack<String> capacities = new Stack<>();
            for (int i = 0; i < cc3.result.size(); i++) {
                idroom.add(cc2.result.get(i));
                capacities.add(cc2.result.get(i));
            }
            int people = 0;
            ArrayList<ArrayList<String>> offers = new ArrayList<>();
            ArrayList<String> offer = new ArrayList<>();

            // BUILDING LISTS OF IDs - EACH LIST WILL GIVE AN OFFER
            while (!idroom.isEmpty() && !capacities.isEmpty()) {
                while (people < Integer.parseInt(nbpeople.getText())) {
                    offer.add(idroom.peek());
                    people += Integer.parseInt(capacities.peek());
                    idroom.pop();capacities.pop();
                }
                offers.add(offer);
                offer.clear();
                people = 0;
            }
            ArrayList<OneOffer> finaloffers = new ArrayList<>();
            commands.clear();
            String command2 = "";

            // FOR EACH LIST/OFFER IN OFFERS MAKE A ONEOFFER OBJECT
            for (ArrayList<String> list: offers) {
                System.out.println(list);
                System.out.println("############## fin d'offre #################");
                for (String id: list) {
                    
                    command = "show"; command1 = "room"; command2 = id;
                    commands.add(command); commands.add(command1); commands.add(command2);
                    CCSocketTCPbis cc4 = new CCSocketTCPbis(commands);
                    commands.clear();
                    command = "getprice(" + id + ");"; commands.add(command);
                    CCSocketTCPbis cc5 = new CCSocketTCPbis(commands);
                    finaloffers.add(new OneOffer(id,cc4.result.get(0),cc5.result.get(0)));
                }         
            }

            // ADD ALL ONEOFFERs IN A LIST AND GO TO THE NEXT PAGE WITH
            Offers ofpage = new Offers(new OneOffer(finaloffers)));
        }
    }

    public static void main(String[] args) {
        Search fen = new Search();
    }
}
