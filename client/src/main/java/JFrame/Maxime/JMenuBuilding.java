package JFrame.Maxime;

import javax.swing.*;
import java.util.ArrayList;

public class JMenuBuilding {
    JMenu jb;
    ArrayList<JMenuFloor> jf = new ArrayList<>();
    public JMenuBuilding(String jb){
        this.jb = new JMenu(jb);
    }
    public boolean contains(JMenuFloor j){
        return jf.contains(j);
    }
    public boolean contains(JMenuItem j){
        for (int k = 0; k<jf.size();k++){
            if(jf.get(k).jr.contains(j)){
                return true;
            }
        }
        return false;
    }
}
