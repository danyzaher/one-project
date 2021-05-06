package jframe.dany;

import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OneOffer extends JPanel implements ActionListener {
    JPanel left = new JPanel();
    JPanel right = new JPanel();
    JLabel title;
    JLabel price;
    JButton button = new JButton("choisir");
    ArrayList<String> idroom;
    String companyName;
    public OneOffer (ArrayList<String> id, String ti, String pri, String cp) {
        companyName=cp;
        title = new JLabel("SALLES : " + ti);
        button.addActionListener(this);
        price = new JLabel(pri);
        idroom=id;
        left.setLayout(new BoxLayout(left,BoxLayout.LINE_AXIS));
        right.setLayout(new BoxLayout(right,BoxLayout.LINE_AXIS));
        left.add(title);
        setBorder(BorderFactory.createLineBorder(Color.PINK, 20));
        right.add(new JLabel(price.getText() + "€"));
        left.add(button);
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(left);this.add(right);
    }

    public JButton getButton() {
        return button;
    }

    public String getPrice() {
        return price.getText();
    }

    public ArrayList<String> getId() {
        return idroom;
    }

    public String getTitle() {
        return title.getText();
    }

    @Override
    public String toString() {
        return "OneOffer{" +
                "title=" + title.getText() +
                ", pri=" + price.getText() +
                ", idroom='" + idroom + '\'' +
                '}';
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        OfferSelected of = new OfferSelected(this,companyName);
    }
}
