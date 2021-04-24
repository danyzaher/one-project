package jFrame.maxime.button;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Sensor extends JButton implements ActionListener, Border {
    public String name;
    final int WIDTH=11;
    final int HEIGHT=11;
    public boolean etat;
    public int id;
    ArrayList<String> result;
    private int r = 15;
     public String roomName;
    public Sensor(String name,int x,int y,int id,String roomName){
        this.name = name;
        this.roomName = roomName;
        this.id=id;
        addActionListener(this);
        this.setBorder(this);
        setBounds(x,y,WIDTH,HEIGHT);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.r+1, this.r+1, this.r+2, this.r);
    }
    public boolean isBorderOpaque() {
        return true;
    }
    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, r, r);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
