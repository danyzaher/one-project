package jframe.maxime.button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoBackMenu extends JMenuItem implements ActionListener{
    JFrame jFrameB;
    JFrame jFrameA;
    public GoBackMenu(JFrame jFrameA, JFrame jFrameB){
        this.jFrameB=jFrameB;
        this.jFrameA=jFrameA;
        jFrameB.setVisible(false);
        addActionListener(this);
        setText("Retour");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jFrameA.setVisible(false);
        jFrameB.setVisible(true);
    }
}
