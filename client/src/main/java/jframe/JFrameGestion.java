package jframe;

import javax.swing.*;
import java.util.LinkedList;

public class JFrameGestion {
    LinkedList<JFrame> visibleJFrame = new LinkedList<>();
    public void addJFrame(JFrame j){
        if (visibleJFrame.isEmpty()){
            visibleJFrame.add(j);
        }
        else{
            visibleJFrame.get(0).setVisible(false);
            visibleJFrame.add(j);
        }
    }
    public void deleteJFrame(){
        if (!visibleJFrame.isEmpty()){
            visibleJFrame.get(0).setVisible(false);
            visibleJFrame.remove(0);
            visibleJFrame.get(0).setVisible(true);
        }
    }
}
