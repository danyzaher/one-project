package jframe;

import javax.swing.*;
import java.util.ArrayList;

public class JMenuFloor {
    JMenu jf;
    ArrayList<JMenuItem> jr = new ArrayList<>();
    public JMenuFloor(String jf){
        this.jf= new JMenu(jf);
    }
}
