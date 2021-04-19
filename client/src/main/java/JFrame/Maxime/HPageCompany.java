package JFrame.Maxime;

import Socket.CCSocketTCPbis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HPageCompany extends JFrame implements ActionListener {
    private final static Logger logger = LoggerFactory.getLogger(HPageCompany.class.getName());
    String title;
    JMenu roomlocation = new JMenu("Salles à la location");
    JMenu jMenu = new JMenu("Menu");
    ArrayList<JMenuBuilding> jMenus= new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();
    public HPageCompany(String s){
        logger.info("begin HPageComany "+s);
        title = s;
        setTitle("Page d'accueil de l'entreprise "+s);
        setLayout(new FlowLayout());
        setSize(1000, 900);
        setVisible(true);
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        JMenu badge = new JMenu("Badge");
        getMenu();
        jMenu.add(roomlocation);
        jMenu.add(badge);
        this.setJMenuBar(jMenuBar);
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
            //il y a encore une erreur dans les if else
            if (jMenus.contains(r3)){
                if (jMenus.get(jMenus.indexOf(r3)).contains(r2)){
                    if (jMenus.get(jMenus.indexOf(r3)).contains(r1)){
                        //nothing
                    }
                    else{
                        logger.info("in getMenu if if else ");
                        jMenus.get(jMenus.indexOf(r3)).
                                jf.get(jMenus.get(jMenus.indexOf(r3)).jf.indexOf(r2)).
                                jf.add(r1);
                        jMenus.get(jMenus.indexOf(r3)).
                                jf.get(jMenus.get(jMenus.indexOf(r3)).jf.indexOf(r2)).
                                jr.add(r1);
                    }
                }
                else {
                    logger.info(("in getMenu if else"));

                    r2.jf.add(r1);
                    jMenus.get(jMenus.indexOf(r3)).
                            jf.add(r2);
                    jMenus.get(jMenus.indexOf(r3)).
                            jb.add(r2.jf);
                    jMenus.get(jMenus.indexOf(r3)).
                            jf.get(jMenus.get(jMenus.indexOf(r3)).jf.indexOf(r2)).
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
                roomlocation.add(r3.jb);
            }
        } logger.info("end of for");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int k=0; k<jMenus.size();k++){
            for (int i=0;i<jMenus.get(k).jf.size();i++){
                for(int j=0;j<jMenus.get(k).jf.get(i).jr.size();j++){
                    if(e.getSource()==jMenus.get(k).jf.get(i).jr.get(j)){
                        MapperRoom mapperRoom = new MapperRoom(jMenus.get(k).jf.get(i).jr.get(j).getText());
                    }
                }
            }
        }
        this.setVisible(false);
    }
}
