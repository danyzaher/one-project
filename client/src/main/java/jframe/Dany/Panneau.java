package jframe.Dany;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Panneau extends JPanel {

    public void paintComponent(Graphics g) {
        ArrayList<String> floors = new ArrayList();
        ArrayList<String> buildings = new ArrayList<>();
        buildings.add("b1");buildings.add("b2");
        floors.add("Rez de chaussée");floors.add("Etage 1");floors.add("Etage 2");
        JComboBox jcb = new JComboBox();
        JComboBox jcb1 = new JComboBox();
        jcb.addItem(buildings.get(0));
        jcb.addItem(buildings.get(1));
        for (int i = 0; i<floors.size();i++) {
            jcb.addItem(floors.get(i));
        }
        this.add(jcb1);
        this.add(jcb);
        int x2 = this.getWidth()/2;
        int y2 = this.getHeight()/2;

        int x3 = this.getWidth()/3;
        int y3 = this.getHeight()/3;

        int x4 = this.getWidth()/4;
        int y4 = this.getHeight()/4;

        int x8 = this.getWidth()/8;
        int y8 = this.getHeight()/8;

        Font font = new Font("Courier", Font.BOLD,20);
        g.setFont(font);
        g.drawString("Choisissez un étage",10,30);
        g.drawRect(x4, y4, x2, y2);
        g.setColor(Color.black);
        g.fillRect(x4,y4, x8, y8);
        g.drawRect(x4,y2, x8,y4);
        g.fillRect(x2, y4,x4,y4);
        g.drawRect(x2,y2+30, x4,y4-30);


    }
}