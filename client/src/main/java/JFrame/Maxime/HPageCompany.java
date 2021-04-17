package JFrame.Maxime;

import Socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HPageCompany extends JFrame implements ActionListener {


    String title;
    JMenu roomlocation = new JMenu("Salles à la location");
    JMenu jMenu = new JMenu("Menu");
    HashMap<JMenu,HashMap<JMenu,ArrayList<JMenuItem>>> jMenus= new HashMap<>();
    ArrayList<String> result = new ArrayList<>();
    public HPageCompany(String s){
        title = s;
        setTitle("Page d'accueil de l'entreprise "+s);
        setLayout(new FlowLayout());
        setSize(1000, 900);
        setVisible(true);
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        getMenu();
        JMenu badge = new JMenu("Badge");

        jMenu.add(badge);
        this.setJMenuBar(jMenuBar);
    }
    public void getMenu(){
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add(title);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        for(int k =0; k<result.size()-3;k=k+3){
            JMenuItem r1 = new JMenuItem(result.get(k));
            JMenu r2 = new JMenu(result.get(k+1));
            JMenu r3 = new JMenu(result.get(k+2));
            r1.addActionListener(this);
            if (jMenus.containsKey(r3)){
                if (jMenus.get(r3).containsKey(r2)){
                    if (jMenus.get(r3).get(r2).contains(r1)){
                        //nothing
                    }
                    else{
                        r2.add(r1);
                        jMenus.get(r3).get(r2).add(r1);
                    }
                }
                else {
                    r3.add(r2);
                    r2.add(r1);
                    ArrayList<JMenuItem> jmi = new ArrayList<>();
                    jmi.add(r1);
                    HashMap<JMenu,ArrayList<JMenuItem>> hjmi = new HashMap<>();
                    hjmi.put(r2,jmi);
                }
            } else {
                r3.add(r2);
                r2.add(r1);
                ArrayList<JMenuItem> jmi = new ArrayList<>();
                jmi.add(r1);
                HashMap<JMenu,ArrayList<JMenuItem>> hjmi = new HashMap<>();
                hjmi.put(r2,jmi);
                jMenus.put(r3,hjmi);
                roomlocation.add(r3);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Map.Entry mapentry : jMenus.entrySet()){
            HashMap<JMenu,ArrayList<JMenuItem>> j = (HashMap<JMenu, ArrayList<JMenuItem>>) mapentry.getValue();
            for(Map.Entry map : j.entrySet()){
                ArrayList<JMenuItem> a = (ArrayList<JMenuItem>) map.getValue();
                for(int k=0 ; k<a.size();k++){
                    if(e.getSource().equals(a.get(k))){
                        MapperRoom mapperRoom = new MapperRoom(a.get(k).getName());}
                }
            }
        }
        this.setVisible(false);
    }
}
