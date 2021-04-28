package jframe.danyter;

import javax.swing.*;

public class OneOffer extends JPanel {
    JPanel left = new JPanel();
    JPanel right = new JPanel();
    JLabel title;
    JLabel where;
    JLabel pri;
    JButton button = new JButton("choisir");

    public OneOffer (String ti, String wh, String price) {
        title = new JLabel(ti);
        where = new JLabel(wh);
        pri = new JLabel(price);

        left.setLayout(new BoxLayout(left,BoxLayout.LINE_AXIS));
        right.setLayout(new BoxLayout(right,BoxLayout.LINE_AXIS));
        left.add(title);
        left.add(where);
        left.add(pri);
        right.add(button);
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(left);this.add(right);
    }

    public JButton getButton() {
        return button;
    }

    public JLabel getPrice() {
        return pri;
    }

    public JLabel getTitle() {
        return title;
    }

    public JLabel getWhere() {
        return where;
    }
}
