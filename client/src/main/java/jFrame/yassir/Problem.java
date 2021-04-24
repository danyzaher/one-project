package jFrame.yassir;

import javax.swing.*;
import java.awt.*;

public class Problem extends JFrame {
    public Problem() {
        this.setTitle("OneBuilding");
        this.setSize(900, 300);
        this.setLocationRelativeTo(null);

        JTextField bdg = new JTextField();
        bdg.setPreferredSize(new Dimension(30,10));
        JLabel numbdg=new JLabel("Entrer le numéro du badge concerné:");

        JButton conf = new JButton("Confirmer");

        JPanel fenetre = new JPanel();
        fenetre.setLayout(new BoxLayout(fenetre, BoxLayout.LINE_AXIS));
        fenetre.add(numbdg);
        fenetre.add(bdg);
        fenetre.add(conf);
        this.getContentPane().add(fenetre);
        this.setVisible(true);

    }
    public static void main(String[] args) {
        Problem  prb = new Problem();

    }



}
