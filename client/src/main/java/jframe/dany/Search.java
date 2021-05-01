package jframe.dany;

import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

     public String companyName;
    public Search(String companyName) {
        this.companyName=companyName;
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


            ArrayList<String> commands = new ArrayList<>();
            commands.add("show");
            commands.add("room");
            commands.add("id");
            commands.add(bmin.getText());
            commands.add(bmax.getText());
            socket.CCSocketTCPbis cc = new socket.CCSocketTCPbis(commands);
            System.out.println("GETROOMINB" + cc.result);
            commands.clear();
            // SET A GRADE FOR EACH IDs

            if (sun.isSelected() && height.isSelected()) {
                for (String value : cc.result) {
                    commands.add("setgradesunheight(" + value + ");");
                    new socket.CCSocketTCPbis(commands);
                    commands.clear();

                }
            }
            if (sun.isSelected() && !height.isSelected()) {
                for (String value : cc.result) {
                    commands.add("setgradesun(" + value + ");");
                    new socket.CCSocketTCPbis(commands);
                    commands.clear();

                }
            }
            if (!sun.isSelected() && height.isSelected()) {
                for (String value : cc.result) {
                    commands.add("setgradeheight(" + value + ");");
                    new socket.CCSocketTCPbis(commands);
                    commands.clear();
                }
            }
            if (!sun.isSelected() && !height.isSelected()) {
                for (String value : cc.result) {
                    commands.add("setgrade(" + value + ");");
                    new socket.CCSocketTCPbis(commands);
                    commands.clear();

                }
            }

            // GET IDs AND THE ROOM'S CAPACITY ORDERED BY THEIR GRADE - ASC BECAUSE THE STACK WILL REVERSE THE ORDER
            commands.add("show");
            commands.add("room");
            commands.add("id");
            commands.add(bmin.getText());
            commands.add(bmax.getText());
            CCSocketTCPbis cc2 = new CCSocketTCPbis(commands);
            commands.clear();
            commands.add("show");
            commands.add("room");
            commands.add("capacity");
            commands.add(bmin.getText());
            commands.add(bmax.getText());
            CCSocketTCPbis cc3 = new CCSocketTCPbis(commands);
            commands.clear();
            // USE STACK TO ONLY USE EACH IDs ONE TIME
            Stack<String> idroom = new Stack<>();
            Stack<String> capacities = new Stack<>();
            for (int i = 0; i < cc3.result.size(); i++) {
                idroom.add(cc2.result.get(i));
                capacities.add(cc3.result.get(i));
            }

            System.out.println("IDROOM SIZE : " + idroom.size() + "  CAPACITIES SIZE : " + capacities.size());
            if (idroom.isEmpty()) {
                System.out.println("no offers found retry");
            } else {
                idroom.pop();
                capacities.pop();
                int people = 0;
                ArrayList<ArrayList<String>> offers = new ArrayList<>();

                // BUILDING LISTS OF IDs - EACH LIST WILL GIVE AN OFFER
                while (!idroom.isEmpty() && !capacities.isEmpty()) {
                    ArrayList<String> offer = new ArrayList<>();
                    while (people < Integer.parseInt(nbpeople.getText())) {
                        if (!idroom.isEmpty() && !capacities.isEmpty()) {
                            offer.add(idroom.peek());
                            people += Integer.parseInt(capacities.peek());
                            idroom.pop();
                            capacities.pop();
                        } else {
                            break;
                        }
                    }
                    System.out.println(offer);
                    if (!offer.isEmpty() && people >= Integer.parseInt(nbpeople.getText())) {
                        System.out.println("adding an offer size :" + offer.size());
                        offers.add(offer);
                    }
                    people = 0;
                }
                ArrayList<OneOffer> finaloffers = new ArrayList<>();
                commands.clear();
                // FOR EACH LIST/OFFER IN OFFERS MAKE A ONEOFFER OBJECT
                System.out.println(offers.size());
                for (ArrayList<String> list : offers) {
                    System.out.println("############## debut d'offre #################");
                    System.out.println(list);
                    ArrayList<String> ids = new ArrayList<>();
                    int finalprice = 0;
                    StringBuilder finaltitle = new StringBuilder();
                    for (String id : list) {
                        commands.add("show");
                        commands.add("room");
                        commands.add("name");
                        commands.add(id);
                        CCSocketTCPbis cc4 = new CCSocketTCPbis(commands);
                        commands.clear();
                        commands.add("room"); commands.add("price"); commands.add(id); commands.add(String.valueOf(electrofen.isSelected()));
                        CCSocketTCPbis cc5 = new CCSocketTCPbis(commands);

                        System.out.println(cc5.result);
                        finaltitle.append(cc4.result);
                        ids.add(id);
                        finalprice += Integer.parseInt(cc5.result.get(0));
                    }
                    finaloffers.add(new OneOffer(ids, finaltitle.toString(), String.valueOf(finalprice)));
                }

                // ADD ALL ONEOFFERs IN A LIST AND GO TO THE NEXT PAGE WITH
                Offers ofpage = new Offers(finaloffers, companyName);
            }
        }
    }

    public static void main(String[] args) {
        Search fen = new Search("Fritel Inc.");
    }
}
