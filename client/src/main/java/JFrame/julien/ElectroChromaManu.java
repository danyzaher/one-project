package JFrame.julien;

import JFrame.Maxime.HPageCompany;
import Socket.CCSocketTCPbis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ElectroChromaManu extends JFrame {

    private final static Logger logger = LoggerFactory.getLogger(HPageCompany.class.getName());
    int opacity;
    String id;

    ArrayList<String> result;
    JSlider opac;


    public ElectroChromaManu(String id) {
        opac = new JSlider(JSlider.HORIZONTAL, 0, 5, 5);
        opac.setMajorTickSpacing(1);
        opac.setPaintTicks(true);
        add(opac);

        this.id = id;
        setSize(400, 400);
        setResizable(false);
        setTitle("Parametre manuel de l'option electrochroma");
        setLayout(new FlowLayout());
        setVisible(true);
        getOpacityValue();



    }

    public void getOpacityValue(){

        logger.info("begin getOpacityValue");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("opacity");
        stringArrayList.add(id);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        opacity = Integer.parseInt(result.get(0));
        logger.info("id = " + opacity);

    }
}
