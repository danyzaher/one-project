package jframe.dany;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Offers extends JFrame implements ActionListener {
    JPanel bigpan = new JPanel();
    JPanel pantitle = new JPanel();
    JLabel title = new JLabel("CHOISISSEZ UNE OFFRE");
    ArrayList<jframe.dany.OneOffer> offers;
    String companyName;
    public Offers(ArrayList<jframe.dany.OneOffer> list, String companyName) {
        this.companyName = companyName;
        offers = list;
        setTitle("Offres");
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        pantitle.add(title);
        pantitle.setLayout(new BoxLayout(pantitle,BoxLayout.LINE_AXIS));
        bigpan.setLayout(new BoxLayout(bigpan,BoxLayout.PAGE_AXIS));
        bigpan.add(pantitle);
        for (jframe.dany.OneOffer offer : offers) {
            bigpan.add(offer);
        }
        this.add(bigpan);
        setSize(50*title.getText().length(),100+50*list.size());
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (OneOffer offer : offers) {
            if (actionEvent.getSource() == offer.getButton()) {
                OfferSelected of = new OfferSelected(offer, companyName);
                break;
            }
        }
    }
}
