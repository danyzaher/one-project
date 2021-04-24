package jFrame.danyter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Offers extends JFrame implements ActionListener {
    JPanel pan = new JPanel();
    JLabel title = new JLabel("Choisissez une offre");
    int nboffers;
    ArrayList<OneOffer> offers;
    public Offers(ArrayList<OneOffer> list) {
        offers = list;
        setTitle("Offres");
        setSize(400,300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        nboffers = 5;
        pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
        pan.add(title);
        for (OneOffer offer : offers) {
            pan.add(offer);
        }

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
