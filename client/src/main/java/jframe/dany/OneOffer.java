package jframe.dany;

import socket.CCSocketTCPbis;

import javax.swing.*;
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
    public OneOffer (ArrayList<String> id, String ti, String pri) {
        title = new JLabel("SALLES : " + ti);
    
        price = new JLabel("PRIX : " + pri);
        idroom=id;
        left.setLayout(new BoxLayout(left,BoxLayout.LINE_AXIS));
        right.setLayout(new BoxLayout(right,BoxLayout.LINE_AXIS));
        left.add(title);

        right.add(price);
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
    public int count() {return idroom.size();}

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
        if (actionEvent.getSource() == button) {
            for (String value : idroom) {
                System.out.println("ACTION PERFORMED");
                ArrayList<String> commands = new ArrayList<>();
                commands.add("show");
                commands.add("company");
                commands.add("id");
                commands.add(value);
                CCSocketTCPbis cc = new CCSocketTCPbis(commands);
                ArrayList<String> al = new ArrayList<>();
                al.add("insert");
                al.add("location");
                al.add(price.getText());
                al.add(cc.result.get(0));
                al.add(value);
                new CCSocketTCPbis(al);
            }
            Success s = new Success();
        }
    }
}
