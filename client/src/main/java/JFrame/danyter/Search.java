package JFrame.danyter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search  extends JFrame implements ActionListener {
    JPanel bigpan = new JPanel();
    JPanel pan1 = new JPanel();
    JPanel pan2 = new JPanel();
    JPanel pan3 = new JPanel();
    JPanel pan4 = new JPanel();
    JPanel pan5 = new JPanel();
    JPanel pan6 = new JPanel();
    JPanel pan7 = new JPanel();
    JLabel title = new JLabel("Entrez vos critères de recherche");
    JLabel lsun = new JLabel();
    JLabel lfiber = new JLabel();
    JLabel lheight = new JLabel();
    JLabel lbmin = new JLabel("Budget min");
    JLabel lbmax = new JLabel("Budget max");
    JLabel lnbpeople = new JLabel();
    JTextField bmin = new JTextField();
    JTextField bmax = new JTextField();
    JTextField nbpeople = new JTextField();
    JCheckBox sun = new JCheckBox();
    JCheckBox fiber = new JCheckBox();
    JCheckBox height = new JCheckBox();
    JButton done = new JButton("Rechercher");

    public Search() {
        setTitle("Recherche");
        setVisible(true);
        setSize(400,300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pan1.add(title);
//        pan.add(lsun);
//        pan.add(lfiber);
//        pan.add(lheight);
        pan2.add(lbmin);
        pan3.add(lbmax);
//        pan.add(lnbpeople);
        pan2.add(bmin);
        pan3.add(bmax);
//        pan.add(nbpeople);
//        pan.add(sun);
//        pan.add(fiber);
//        pan.add(height);
//        pan.add(done);
        bmin.setSize(30,30);
        pan2.setLayout(new BoxLayout(pan2,BoxLayout.LINE_AXIS));
        pan3.setLayout(new BoxLayout(pan3,BoxLayout.LINE_AXIS));
        bigpan.add(pan1);
        bigpan.add(pan2);
        bigpan.add(pan3);
        bigpan.add(done);
        bigpan.setLayout(new BoxLayout(bigpan,BoxLayout.PAGE_AXIS));
        this.add(bigpan);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    public static void main(String[] args) {
        Search fen = new Search();
    }
}
