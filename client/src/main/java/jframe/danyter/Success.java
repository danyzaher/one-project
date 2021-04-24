package jframe.danyter;

import javax.swing.*;

public class Success extends JFrame {
    JPanel pan = new JPanel();
    JLabel success = new JLabel("Votre demande de location a bien été enregistrée");
    public Success() {
        setTitle("Succès");
        setVisible(true);
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pan.add(success);
    }
}
