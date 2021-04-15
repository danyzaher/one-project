package JFrame.Maxime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Emplacement extends JButton implements ActionListener {
    int x;
    int y;
    boolean etat = true;

    public Emplacement(int x,int y){
        this.x = x;
        this.y = y;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
