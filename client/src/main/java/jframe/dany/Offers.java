package jframe.dany;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Offers extends JFrame implements ActionListener {
    JPanel pan = new JPanel();
    JLabel title = new JLabel("Choisissez une offre");
    int nboffers;
    ArrayList<jframe.dany.OneOffer> offers;
    String companyName;
    public Offers(ArrayList<jframe.dany.OneOffer> list, String companyName) {
        this.companyName = companyName;
        offers = list;
        setTitle("Offres");
        setSize(400,300);
        setLayout(new FlowLayout());
        nboffers = 5;
        pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
        pan.add(title);
        for (jframe.dany.OneOffer offer : offers) {
            pan.add(offer);
        }

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (int i = 0; i < offers.size(); i++) {
            if (actionEvent.getSource() == offers.get(i).getButton()) {
                OfferSelected of = new OfferSelected(offers.get(i),companyName);
                break;
            }
        }
    }
}
