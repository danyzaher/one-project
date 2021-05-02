package jframe.maxime.button;

import jframe.HPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoBackMenuTotal extends JMenuItem implements ActionListener {
    JFrame jFrameA;
    public GoBackMenuTotal(JFrame jFrameA){
        this.jFrameA=jFrameA;
        addActionListener(this);
        setText("Retour au départ");}
    public void actionPerformed(ActionEvent e) {
        jFrameA.setVisible(false);
        HPage h=new HPage();
    }
}
