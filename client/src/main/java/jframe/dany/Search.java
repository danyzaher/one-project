package jframe.dany;

import jframe.maxime.button.GoBackButton;
import jframe.maxime.button.GoBackMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

public class Search  extends JFrame implements ActionListener {
    protected static Logger SearchLog  = LoggerFactory.getLogger("Search");
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
    JCheckBox electrowin = new JCheckBox();
    JCheckBox height = new JCheckBox();
    JButton done = new JButton("Rechercher");
    GoBackButton gbb;

     public String companyName;
    public Search(String companyName) {

        this.companyName=companyName;
        setTitle("Recherche");

        setSize(400,300);
        setResizable(false);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        pan5.add(electrowin);
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
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == done) {
            if (Integer.parseInt(bmin.getText()) < Integer.parseInt(bmax.getText()) && !bmin.getText().equals("") && !bmax.getText().equals("") && !nbpeople.getText().equals("")) {
                ArrayList<String> commands = new ArrayList<>();
                commands.add("show");
                commands.add("room");
                commands.add("id");
                socket.CCSocketTCPbis cc = new socket.CCSocketTCPbis(commands);
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

                // WE ALREADY HAVE THE IDS, WE NEED THE ROOMS' CAPACITY ORDERED BY THEIR GRADE - ASC BECAUSE THE STACK WILL REVERSE THE ORDER
                commands.clear();
                commands.add("show");
                commands.add("room");
                commands.add("capacity");

                CCSocketTCPbis cc3 = new CCSocketTCPbis(commands);
                commands.clear();
                // USE STACK TO ONLY USE EACH IDs ONE TIME
                Stack<String> idroom = new Stack<>();
                Stack<String> capacities = new Stack<>();

                for (int i = 0; i < cc3.result.size() - 1; i++) {
                    idroom.add(cc.result.get(i));
                    capacities.add(cc3.result.get(i));
                }


                SearchLog.info("IDROOM SIZE : " + idroom.size() + "  CAPACITIES SIZE : " + capacities.size());
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
                    if (!offer.isEmpty() && people >= Integer.parseInt(nbpeople.getText())) {
                        offers.add(offer);
                    }
                    people = 0;
                }
                ArrayList<OneOffer> finaloffers = new ArrayList<>();
                commands.clear();
                // FOR EACH LIST/OFFER IN OFFERS MAKE A ONEOFFER OBJECT

                for (ArrayList<String> list : offers) {
                    SearchLog.info("############## begin offer #################");
                    SearchLog.info(String.valueOf(list));
                    ArrayList<String> ids = new ArrayList<>();
                    int finalprice = 0;
                    String finaltitle = "";
                    for (String id : list) {
                        commands.add("show");
                        commands.add("room");
                        commands.add("name");
                        commands.add(id);
                        CCSocketTCPbis cc4 = new CCSocketTCPbis(commands);
                        commands.clear();
                        commands.add("show");
                        commands.add("room");
                        commands.add("price");
                        commands.add(id);
                        commands.add(String.valueOf(electrowin.isSelected()));
                        CCSocketTCPbis cc5 = new CCSocketTCPbis(commands);
                        finaltitle += cc4.result.get(0) + " - ";


                        ids.add(id);
                        System.out.println(cc4.result);
                        System.out.println(cc5.result);
                        finalprice += Integer.parseInt(cc5.result.get(0));
                        commands.clear();
                    }


                    finaltitle = finaltitle.substring(0, finaltitle.length() - 1);
                    finaltitle = finaltitle.substring(0, finaltitle.length() - 1);
                    System.out.println(finalprice);
                    System.out.println(finaltitle);
                    // KEEP ONLY THE OFFERS THAT ARE IN THE CLIENT'S BUDGET +-10%
                    double min = Integer.parseInt(bmin.getText());
                    double max = Integer.parseInt(bmax.getText());
                    if (finalprice > min && finalprice < max)
                        finaloffers.add(new OneOffer(ids, finaltitle, String.valueOf(finalprice), companyName));

                }
                if (finaloffers.isEmpty()) {
                    SearchLog.info("no offers found retry");
                    JOptionPane.showMessageDialog(new JPanel(), "Aucunes offres ne correspondent à vos critères de recherche, veuillez en saisir de nouveaux", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (OneOffer offer : finaloffers) {
                        System.out.println(offer);
                    }
                    // GO TO THE NEXT PAGE
                    Offers ofpage = new Offers(finaloffers, companyName);
                    ofpage.newGoBack(this);
                }
            }
        } else {
            if (Integer.parseInt(bmin.getText()) > Integer.parseInt(bmax.getText())) {
                SearchLog.info("min > max");
                JOptionPane.showMessageDialog(bigpan, "Entrez un budget minimum inférieur au maximum", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                SearchLog.info("missing value");
                JOptionPane.showMessageDialog(bigpan, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void newGoBack(JFrame j){
        gbb = new GoBackButton(this,j);
        add(gbb);
    }

    public static void main(String[] args) {
        Search fen = new Search("Apple");
    }
}
