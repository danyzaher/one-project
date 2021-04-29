package jframe.maxime.button;

import jframe.maxime.window.MapperRoom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaceMove extends JButton implements ActionListener {
    public int x;
    public int y;
    public int id2;
    public String id;
    MapperRoom mapperRoom;
    public PlaceMove(int x, int y, int id2,MapperRoom mapperRoom,String id){
        this.mapperRoom=mapperRoom;
        this.id=id;
        this.id2=id2;
        this.x = x;
        this.y = y;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
