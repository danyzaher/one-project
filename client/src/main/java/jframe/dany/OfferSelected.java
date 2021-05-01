package jframe.dany;

import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OfferSelected extends JFrame implements ActionListener{
    JPanel bigpan = new JPanel();
    JPanel right = new JPanel();
    JPanel left = new JPanel();
    JButton rent = new JButton("Louer");

    jframe.dany.OneOffer oneOffer;
    JLabel desc;
    String companyName;
    public OfferSelected(jframe.dany.OneOffer on, String companyName) {
        this.companyName=companyName;
        oneOffer = on;
        setTitle("Détails de l'offre");
        setLocationRelativeTo(null);
        setSize(400, 100);
        setResizable(true);
        setLayout(new FlowLayout());
        desc = new JLabel(oneOffer.getTitle() + "  " + oneOffer.getPrice());
        left.add(desc);
        right.add(rent);
        rent.addActionListener(this);
        right.setLayout(new BoxLayout(right, BoxLayout.LINE_AXIS));
        left.setLayout(new BoxLayout(left, BoxLayout.LINE_AXIS));
        bigpan.add(right);
        bigpan.add(left);
        bigpan.setLayout(new BoxLayout(bigpan, BoxLayout.PAGE_AXIS));
        this.add(bigpan);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == rent) {
            for (int i = 0; i < oneOffer.getId().size(); i++) {
                ArrayList<String> commands = new ArrayList<>();
                commands.add("show");
                commands.add("company");
                commands.add("id");
                commands.add(companyName);
                CCSocketTCPbis cc = new CCSocketTCPbis(commands);
                System.out.println(cc.result);
                ArrayList<String> al = new ArrayList<>();
                al.add("insert");
                al.add("location");
                al.add(oneOffer.getPrice());
                al.add(cc.result.get(0));
                al.add(oneOffer.getId().get(i));
                new CCSocketTCPbis(al);
            }
            Success s = new Success();
        }
    }
}
