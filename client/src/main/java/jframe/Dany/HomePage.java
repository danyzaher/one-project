package jframe.Dany;

import javax.swing.*;
import java.util.ArrayList;

public class HomePage extends JPanel {
    private JComboBox jComboBox;
    private JButton jButton;
    private ArrayList<String> companies;
    public HomePage() {
        companies = new ArrayList<>();
        companies.add("CleanPerfect"); companies.add("FloraFlore"); companies.add("Fritel Inc.");
        jComboBox = new JComboBox(); jComboBox.addItem(companies.get(0));jComboBox.addItem(companies.get(1));
        jButton = new JButton("Confirmer");
        jButton.setBounds(500,550,20,20);jComboBox.setBounds(500,500,20,20);
        this.add(jComboBox);this.add(jButton);
    }
}
