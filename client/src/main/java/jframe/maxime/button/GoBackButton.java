package jframe.maxime.button;

import jframe.HPageCompany;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoBackButton extends JButton implements ActionListener {
    JFrame jFrameB;
    JFrame jFrameA;
    public GoBackButton(JFrame jFrameA, JFrame jFrameB){
        this.jFrameB=jFrameB;
        this.jFrameA=jFrameA;
        jFrameB.setVisible(false);
        addActionListener(this);
        setText("Retour");
        setBounds(50,400,80,50);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jFrameA.setVisible(false);
        jFrameB.setVisible(true);
        if(jFrameB.getClass().equals( HPageCompany.class)){
            HPageCompany hPageCompany = (HPageCompany) jFrameB;
            hPageCompany.getMenu();
        }
    }
}