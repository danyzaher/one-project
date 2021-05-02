package jframe.julien;
import jframe.HPageCompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.*;

import jframe.maxime.button.GoBackButton;
import jframe.maxime.button.GoBackMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.CCSocketTCPbis;


public class ElectroChromaAuto extends JFrame implements ActionListener {
    private final static Logger logger = LoggerFactory.getLogger(HPageCompany.class.getName());

    int temperature;
    int light;

    /*** COMPONENTS FOR MODIFICATION OF THE TEMPERATURES IN ROOMS ***/
    JSlider Stemp;
    JLabel ATemp;

    /*** COMPONENTS FOR MODIFICATION OF THE LIGHTS IN ROOMS ***/
    JSlider Slight;
    JLabel ALight;

    /** VARIABLES FOR EXTERN TEMPERATURE **/
    int temperatureext;
    ArrayList<String> tempresult;
    JLabel Atempext;

    /** VARIABLES FOR EXTERN TEMPERATURE **/
    String temperatureint;
    ArrayList<String> tempintresult;

    /*** BUTTON ***/
    JButton validation;
    GoBackButton goBackButton;

    //Others
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
        validation.addActionListener(this);

        //Temperature ext part

        Atempext = new JLabel("Temperature exterieur :" + temperatureext + " °C");

        //Temperature modification part

        Stemp = new JSlider(JSlider.HORIZONTAL, 0, 30, temperature);
        Stemp.setMajorTickSpacing(1);
        Stemp.setPaintTicks(true);

        eventTemp et = new eventTemp();
        Stemp.addChangeListener(et);

        //Light part

        Slight = new JSlider(JSlider.HORIZONTAL, 0, 5000, light);
        Slight.setMajorTickSpacing(500);
        Slight.setPaintTicks(true);

        /** METHODS **/
        getTempExt();

        /** TEMPERATURES AND LIGHT **/

        ATemp = new JLabel("Temperature souhaitée dans les salles : " + temperature + "°C");
        Atempext = new JLabel("Temperature exterieur :" + temperatureext + " °C");
        ALight = new JLabel("Eclairage souhaitée dans les salles : " + light + " lux");

        /** FRAME ADD **/

        //General information
        add(Atempext);

        //Modification part

        add(ATemp);
        add(Stemp);
        add(ALight);
        add(Slight);
        add(validation);

        eventLight el = new eventLight();
        Slight.addChangeListener(el);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(validation)) {

            logger.info("begin validation for automatic ElectroChroma settings ");
            ArrayList<String> stringArrayList = new ArrayList<>();
            stringArrayList.add("update");
            stringArrayList.add("parameters");
            stringArrayList.add(Stemp.getValue() + "");
            stringArrayList.add(Slight.getValue() + "");
            stringArrayList.add(companyName);
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

    public void getTempExt(){

        logger.info("begin getTempExt");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("temperatureext");
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.tempresult = ccSocketTCP2.result;
        temperatureext = Integer.parseInt(tempresult.get(0));
        logger.info("temperatureext = " + temperatureext);

    }
    public void getGeneralTempLigInt(){

        logger.info("begin getGeneralTempLigInt");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("temperatureGeneralTempLigint");
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.tempintresult = ccSocketTCP2.result;
        temperatureint = tempintresult.get(0);
        logger.info("temperatureint = " + temperatureint);

    }

    public void newGoBack(JFrame j){
        goBackButton = new GoBackButton(this,j);
        add(goBackButton);
    }
}