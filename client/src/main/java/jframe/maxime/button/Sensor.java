package jframe.maxime.button;

import jframe.maxime.window.MapperRoom;
import jframe.maxime.window.WindowSensor;
import socket.CCSocketTCPbis;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Sensor extends JButton implements ActionListener, Border {
    public String name;
    private final int WIDTH=11;
    private final int HEIGHT=11;
    public boolean animated;
    public int id;
    private ArrayList<String> result;
    private final int r = 15;
     public String roomName;
     public MapperRoom mapperRoom;
     public int x;
     public int y;
    public Sensor(String name,int x,int y,int id,String roomName, MapperRoom mapperRoom){
        this.x=x;
        this.y=y;
        this.mapperRoom=mapperRoom;
        this.name = name;
        this.roomName = roomName;
        this.id=id;
        addActionListener(this);
        this.setBorder(this);
        setBounds(x,y,WIDTH,HEIGHT);
        getAnimated();
    }

    private void getAnimated() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("sensor");
        stringArrayList.add("animated");
        stringArrayList.add("" + id);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        animated = result.get(0).equals("t");
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
        WindowSensor windowSensor = new WindowSensor(this);
        windowSensor.newGoBack(mapperRoom);
    }
}
