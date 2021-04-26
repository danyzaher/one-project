package jframe.julien;
import jframe.HPageCompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.CCSocketTCPbis;


public class ElectroChromaAuto extends JFrame implements ActionListener {
    private final static Logger logger = LoggerFactory.getLogger(HPageCompany.class.getName());

    int temperature;
    int light;

    /*** SLIDERS ***/
    JSlider Stemp;
    JSlider Slight;

    /*** LABELS ***/
    JLabel ATemp;
    JLabel ALight;


    /*** BUTTON ***/

    JButton validation;

    String companyName;

    public ElectroChromaAuto(String companyName) {
        this.companyName = companyName;

        /** FRAME SETUP **/

        setSize(600, 400);
        setResizable(false);
        setTitle("Parametres automatiques de l'option electrochroma");
        setLayout(new FlowLayout());

        validation = new JButton("VALIDER");
        validation.setSize(100, 100);

        //Temperature part

        Stemp = new JSlider(JSlider.HORIZONTAL, 0, 30, temperature);
        Stemp.setMajorTickSpacing(1);
        Stemp.setPaintTicks(true);


        ATemp = new JLabel("Temperature souhaitée dans les salles : " + temperature + "°C");
        add(ATemp);
        add(Stemp);
        eventTemp et = new eventTemp();
        Stemp.addChangeListener(et);

        //Light part

        Slight = new JSlider(JSlider.HORIZONTAL, 0, 5000, light);
        Slight.setMajorTickSpacing(100);
        Slight.setPaintTicks(true);


        ALight = new JLabel("Eclairage souhaitée dans les salles : " + light + " lux");
        add(ALight);
        add(Slight);
        add(validation);
        eventLight el = new eventLight();
        Slight.addChangeListener(el);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) { //--> Work in progress

        if(e.getSource().equals(validation)) {

            logger.info("begin validation for automatic ElectroChroma settings ");
            ArrayList<String> stringArrayList = new ArrayList<>();
            stringArrayList.add("update");
            stringArrayList.add("temperature");
            stringArrayList.add("light");
            stringArrayList.add(Stemp.getValue() + "");
            stringArrayList.add(Slight.getValue() + "");
            CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);

        }
    }


    public class eventTemp implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent et) {

            int valuetemp = Stemp.getValue();
            ATemp.setText("Temperature dans les salles souhaitée : " + valuetemp + " ° C");
        }
    }

    public class eventLight implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent el) {

            int valuelight = Slight.getValue();
            ALight.setText("Eclairage dans les salles souhaité : " + valuelight + " lux");
        }
    }

}