package jframe.dany;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Offers extends JFrame implements ActionListener {
    JPanel bigpan = new JPanel();
    JLabel title = new JLabel("Choisissez une offre");
    ArrayList<jframe.dany.OneOffer> offers;
    String companyName;
    public Offers(ArrayList<jframe.dany.OneOffer> list, String companyName) {
        this.companyName = companyName;
        offers = list;
        setTitle("Offres");
        setSize(400,300);
        setLayout(new FlowLayout());
        bigpan.setLayout(new BoxLayout(bigpan,BoxLayout.PAGE_AXIS));
        bigpan.add(title);
        for (jframe.dany.OneOffer offer : offers) {
            bigpan.add(offer);
        }
        this.add(bigpan);
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
