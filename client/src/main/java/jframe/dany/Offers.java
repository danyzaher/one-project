package jframe.dany;


import jframe.maxime.button.GoBackButton;
import jframe.maxime.button.GoBackMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


public class Offers extends JFrame implements ActionListener {
    JPanel bigpan = new JPanel();
    JPanel pantitle = new JPanel();
    JLabel title = new JLabel("CHOISISSEZ UNE OFFRE");
    ArrayList<jframe.dany.OneOffer> offers;
    String companyName;
    GoBackButton goBackbutton;
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
        Collections.sort(offers, new SortByPrice());
        int i = 0;
        for (jframe.dany.OneOffer offer : offers) {
            bigpan.add(offer);
            i++;
            if (i >= 10) {
                break;
            }
        }
        this.add(bigpan);
        setSize(50*title.getText().length(),100+30*list.size());
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
    public void newGoBack(JFrame j){
        goBackbutton = new GoBackButton(this,j);
        add(goBackbutton);
    }

}
