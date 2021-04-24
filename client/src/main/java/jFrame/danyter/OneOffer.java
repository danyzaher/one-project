package jFrame.danyter;

import javax.swing.*;

public class OneOffer extends JPanel {
    JPanel left = new JPanel();
    JPanel right = new JPanel();
    JLabel title;
    JLabel where;
    JLabel potentialprice;
    JButton button = new JButton("choisir");

    public OneOffer (String ti, String wh, String price) {
        title = new JLabel(ti);
        where = new JLabel(wh);
        potentialprice = new JLabel(price);

        left.setLayout(new BoxLayout(left,BoxLayout.LINE_AXIS));
        right.setLayout(new BoxLayout(right,BoxLayout.LINE_AXIS));
        left.add(title);
        left.add(where);
        left.add(potentialprice);
        right.add(button);
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(left);this.add(right);
    }
}
