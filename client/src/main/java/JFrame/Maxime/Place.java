package JFrame.Maxime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Place extends JButton implements ActionListener {
    int x;
    int y;
    int id;
    MapperRoom mapperRoom;
    public Place(int x, int y, int id,MapperRoom mapperRoom){
        this.mapperRoom=mapperRoom;
        this.id=id;
        this.x = x;
        this.y = y;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mapperRoom.setEquipement(this);
    }
}
