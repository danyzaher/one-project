package jframe.danyter;

import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OfferSelected extends JFrame implements ActionListener {
    JPanel bigpan = new JPanel();
    JLabel description = new JLabel("Batiment D 15202");
    JPanel right = new JPanel();
    JPanel left = new JPanel();
    JButton rent = new JButton("Louer");
    jframe.danyter.OneOffer oneOffer;
    String companyName;
    public OfferSelected(jframe.danyter.OneOffer on,String companyName) {
        this.companyName=companyName;
        oneOffer = on;
        setTitle("Détails de l'offre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 300);
        setResizable(false);
        setLayout(new FlowLayout());
        right.setLayout(new BoxLayout(right, BoxLayout.LINE_AXIS));
        left.setLayout(new BoxLayout(left, BoxLayout.LINE_AXIS));
        bigpan.add(right);
        bigpan.add(left);
        bigpan.setLayout(new BoxLayout(bigpan, BoxLayout.PAGE_AXIS));
        this.add(bigpan);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == rent) {
            ArrayList<String> al = new ArrayList<>();
            al.add("update");
            al.add("location");
            al.add(oneOffer.getPrice());
            al.add(IDCOMPANY);
            al.add(oneOffer.getId());
            CCSocketTCPbis cc = new CCSocketTCPbis(al);
            jframe.danyter.Success s = new jframe.danyter.Success();
        }
    }
}
