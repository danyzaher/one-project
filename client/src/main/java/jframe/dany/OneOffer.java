package jframe.dany;

import javax.swing.*;

public class OneOffer extends JPanel {
    JPanel left = new JPanel();
    JPanel right = new JPanel();
    JLabel title;
    JLabel pri;
    JButton button = new JButton("choisir");
    String idroom;
    public OneOffer (String id, String ti,String price) {
        title = new JLabel(ti);
    
        pri = new JLabel(price);
        idroom=id;
        left.setLayout(new BoxLayout(left,BoxLayout.LINE_AXIS));
        right.setLayout(new BoxLayout(right,BoxLayout.LINE_AXIS));
        left.add(title);

        left.add(pri);
        right.add(button);
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(left);this.add(right);
    }

    public JButton getButton() {
        return button;
    }

    public String getPrice() {
        return pri.getText();
    }

    public JLabel getTitle() {
        return title;
    }
    public String getId() {
        return idroom;
    }
}
