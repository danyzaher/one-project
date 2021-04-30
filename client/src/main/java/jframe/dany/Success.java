package jframe.dany;

import javax.swing.*;

public class Success extends JFrame {
    JPanel pan = new JPanel();
    JLabel success = new JLabel("Votre demande de location a bien été enregistrée");
    public Success() {
        setTitle("Succès");
        setVisible(true);
        setSize(400,300);
        pan.add(success);
        pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
        this.add(pan);
    }
}
