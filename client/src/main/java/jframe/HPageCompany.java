package jframe;

import jframe.maxime.button.GoBackMenu;
import jframe.maxime.window.MapperRoom;
import jframe.yassir.Badge;
import jframe.dany.Search;
import jframe.julien.ElectroChromaAuto;
import socket.CCSocketTCPbis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HPageCompany extends JFrame implements ActionListener {
    private final static Logger logger = LoggerFactory.getLogger(HPageCompany.class.getName());
    String title;
    JMenu roomLocation = new JMenu("Salles à la location");
    JMenu jMenu = new JMenu("Menu");
    GoBackMenu goBackMenu;
    JMenuItem badge = new JMenuItem("Badge");
    JMenuItem location = new JMenuItem("Effectuer une nouvelle location");
    JMenuItem chromatique = new JMenuItem("Programmer la luminosité et la température générale");
    ArrayList<JMenuBuilding> jMenus= new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();
    public HPageCompany(String s){
        logger.info("begin HPageCompany "+s);
        title = s;
        setTitle("Page d'accueil de l'entreprise "+s);
        setLayout(new FlowLayout());
        setSize(800, 300);
        setLocationRelativeTo(null);

        setResizable(false);
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        getMenu();
        jMenu.add(roomLocation);
        jMenu.add(badge);
        jMenu.add(location);
        jMenu.add(chromatique);
        badge.addActionListener(this);
        location.addActionListener(this);
        chromatique.addActionListener(this);
        setJMenuBar(jMenuBar);
        setVisible(true);
    }
    public void getMenu(){
        logger.info("begin getMenu");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("menu");
        stringArrayList.add(title);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        logger.info("result = " + result);

        for(int k =0; k<result.size()-3;k=k+3){
            logger.info("in the for "+k);
            JMenuItem r1 = new JMenuItem(result.get(k));
            JMenuFloor r2 = new JMenuFloor(result.get(k+1));
            JMenuBuilding r3 = new JMenuBuilding(result.get(k+2));
            r1.addActionListener(this);
            if (this.containsBuilding(r3)){
                if (containsBuilding(r2)){
                    if (!containsBuilding(r1)){
                        logger.info("in getMenu if if else ");
                        jMenus.get(indexBuilding(r3)).
                                jf.get(indexBuilding(r2)).
                                jf.add(r1);
                        jMenus.get(indexBuilding(r3)).
                                jf.get(indexBuilding(r2)).
                                jr.add(r1);
                    }
                }
                else {
                    logger.info(("in getMenu if else"));

                    r2.jf.add(r1);
                    jMenus.get(indexBuilding(r3)).
                            jf.add(r2);
                    jMenus.get(indexBuilding(r3)).
                            jb.add(r2.jf);
                    jMenus.get(indexBuilding(r3)).
                            jf.get(jMenus.get(indexBuilding(r3)).jf.indexOf(r2)).
                            jr.add(r1);
                }
            } else {
                logger.info("in get Menu else");
                jMenus.add(r3);
                r3.jb.add(r2.jf);
                r2.jf.add(r1);
                jMenus.get(jMenus.indexOf(r3)).
                        jf.add(r2);
                jMenus.get(jMenus.indexOf(r3)).
                        jb.add(r2.jf);
                jMenus.get(jMenus.indexOf(r3)).
                        jf.get(jMenus.get(jMenus.indexOf(r3)).jf.indexOf(r2)).
                        jr.add(r1);
                roomLocation.add(r3.jb);
            }
        } logger.info("end of for");
    }
    public int indexBuilding(JMenuBuilding j){
        for(int k=0; k<jMenus.size();k++){
            if (jMenus.get(k).jb.equals(j.jb)){
                return k;
            }
        }
        return 0;
    }
    public int indexBuilding(JMenuFloor j){
        for (JMenuBuilding menu : jMenus) {
            for (int i = 0; i < menu.jf.size(); i++) {
                if (menu.jf.get(i).jf.equals(j.jf)) {
                    return i;
                }
            }
        }
        return 0;
    }
    public boolean containsBuilding(JMenuBuilding j){
        for (JMenuBuilding menu : jMenus) {
            String s = menu.jb.getText();
            String ss = j.jb.getText();
            if (s.equals(ss)) {
                return true;
            }
        }
        return false;
    }
    public boolean containsBuilding(JMenuFloor j){
        for (JMenuBuilding menu : jMenus) {
            for (int i = 0; i < menu.jf.size(); i++) {
                String s = menu.jf.get(i).jf.getText();
                String ss = j.jf.getText();
                if (s.equals(ss)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean containsBuilding(JMenuItem j){
        for (JMenuBuilding menu : jMenus) {
            for (int i = 0; i < menu.jf.size(); i++) {
                for (int m = 0; m < menu.jf.get(i).jr.size(); m++) {
                    String s = menu.jf.get(i).jr.get(m).getText();
                    String ss = j.getText();
                    if (s.equals(ss)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void newGoBack(JFrame j){
        goBackMenu = new GoBackMenu(this,j);
        jMenu.add(goBackMenu);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (JMenuBuilding menu : jMenus) {
            for (int i = 0; i < menu.jf.size(); i++) {
                for (int j = 0; j < menu.jf.get(i).jr.size(); j++) {
                    if (e.getSource() == menu.jf.get(i).jr.get(j)) {
                        MapperRoom mapperRoom = new MapperRoom(menu.jf.get(i).jr.get(j).getText());
                        mapperRoom.newGoBack(this);
                    }
                }
            }
        }
        if (e.getSource().equals(badge)){
            Badge badge = new Badge(title);
        }
        if (e.getSource().equals(location)){
            Search search = new Search(title);
        }
        if (e.getSource().equals(chromatique)){
            ElectroChromaAuto electroChromaAuto = new ElectroChromaAuto(title);
        }
        this.setVisible(false);
    }


}
