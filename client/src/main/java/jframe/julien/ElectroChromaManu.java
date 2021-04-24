package jframe.julien;

import jframe.HPageCompany;
import socket.CCSocketTCPbis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class ElectroChromaManu extends JFrame {

    private final static Logger logger = LoggerFactory.getLogger(HPageCompany.class.getName());

    int opacity;
    String id;
    ArrayList<String> result;

    JSlider opac;
    JLabel Aopacvalue, ATempExt, ATempInt,ALightInt;



    public ElectroChromaManu(String id) {

        this.id = id;
        setSize(400, 400);
        setResizable(false);
        setTitle("Parametres manuels de l'option electrochroma");
        setLayout(new FlowLayout());

        getOpacityValue(); //Get values of the opacity of the window

        ATempExt = new JLabel("Temperature Externe :");
        add(ATempExt);

        opac = new JSlider(JSlider.HORIZONTAL, 0, 5, opacity);
        opac.setMajorTickSpacing(1);
        opac.setPaintTicks(true);
        Aopacvalue = new JLabel("Opacité souhaitée dans les salles : "+ opacity);

        add(Aopacvalue);
        add(opac);
        eventOpac eo = new eventOpac();
        opac.addChangeListener(eo);



        setVisible(true);

    }
    public class eventOpac implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent eo) {

            int valueopacity = opac.getValue();
            Aopacvalue.setText("Opacité de la fenetre selectionnée souhaité  : " + valueopacity);
        }
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
