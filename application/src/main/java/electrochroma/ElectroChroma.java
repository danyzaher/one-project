package electrochroma;

import javax.swing.*;
import java.awt.FlowLayout;

public class ElectroChroma {


    public static void main(String s[]) {

        JFrame frame = new JFrame("Electrochromatic configuration");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Electrochromatic parameters");
        JButton button = new JButton();
        button.setText("TEST- BUTTON");
        panel.add(label);
        panel.add(button);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}