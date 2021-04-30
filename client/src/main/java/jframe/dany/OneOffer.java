package jframe.dany;

import javax.swing.*;
import java.util.ArrayList;

public class OneOffer extends JPanel {
    JPanel left = new JPanel();
    JPanel right = new JPanel();
    JLabel title;
    JLabel price;
    JButton button = new JButton("choisir");
    ArrayList<String> idroom;
    public OneOffer (ArrayList<String> id, String ti, String pri) {
        title = new JLabel(ti);
    
        price = new JLabel(pri);
        idroom=id;
        left.setLayout(new BoxLayout(left,BoxLayout.LINE_AXIS));
        right.setLayout(new BoxLayout(right,BoxLayout.LINE_AXIS));
        left.add(title);

        left.add(price);
        right.add(button);
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

    @Override
    public String toString() {
        return "OneOffer{" +
                "title=" + title.getText() +
                ", pri=" + price.getText() +
                ", idroom='" + idroom + '\'' +
                '}';
    }
}
