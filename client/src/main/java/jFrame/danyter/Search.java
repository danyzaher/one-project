package jFrame.danyter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == done) {
            Offers of = new Offers(this.getOffers());
        }
    }

    public static void main(String[] args) {
        Search fen = new Search();
    }
    public ArrayList<OneOffer> getOffers() {
        ArrayList<OneOffer> list = new ArrayList<>();

        return list;
    }
}
