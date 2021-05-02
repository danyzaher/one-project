package jframe;

import javax.swing.*;
import java.util.ArrayList;

public class JMenuBuilding {
    JMenu jb;
    ArrayList<JMenuFloor> jf = new ArrayList<>();
    public JMenuBuilding(String jb){
        this.jb = new JMenu(jb);
    }
}
