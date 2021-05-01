package jframe.dany;

import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OfferSelected extends JFrame {
    JPanel bigpan = new JPanel();
    JPanel right = new JPanel();
    JPanel left = new JPanel();
    JButton rent = new JButton("Louer");
    jframe.dany.OneOffer oneOffer;
    String companyName;
    public OfferSelected(jframe.dany.OneOffer on, String companyName) {
        this.companyName=companyName;
        oneOffer = on;
        setTitle("Détails de l'offre");
        setLocationRelativeTo(null);
        setSize(20000, 100);
        setResizable(true);
        setLayout(new FlowLayout());
        right.setLayout(new BoxLayout(right, BoxLayout.LINE_AXIS));
        left.setLayout(new BoxLayout(left, BoxLayout.LINE_AXIS));
        bigpan.add(right);
        bigpan.add(left);
        bigpan.setLayout(new BoxLayout(bigpan, BoxLayout.PAGE_AXIS));
        this.add(bigpan);
        setVisible(true);
    }
}
