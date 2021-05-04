package jframe.maxime.button;

import jframe.maxime.window.MapperRoom;
import jframe.maxime.window.WindowEquipment;
import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Equipment extends JButton implements ActionListener {
    public String name;
    private final int WIDTH=13;
    private final int HEIGHT=13;
    public boolean animated;
    public int id;
    private ArrayList<String> result;
    public String roomName;
    public MapperRoom mapperRoom;
    public int x;
    public int y;
    public Equipment(String name, int x, int y, int id, String roomName, MapperRoom m){
        this.x=x;
        this.y=y;
        this.mapperRoom=m;
        this.roomName = roomName;
        this.id=id;
        this.name = name;
        addActionListener(this);
        getAnimated();
        setBounds(x,y,WIDTH,HEIGHT);
    }
    public void getAnimated() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("equipment");
        stringArrayList.add("animated");
        stringArrayList.add("" + id);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        animated = result.get(0).equals("t");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        WindowEquipment windowEquipment =new WindowEquipment(this);
        windowEquipment.newGoBack(mapperRoom);
    }
}
