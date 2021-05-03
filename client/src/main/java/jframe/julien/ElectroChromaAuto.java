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
    String companyName;

    private Box box;
    private Box box2;

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

    /** VARIABLES FOR INTERN TEMPERATURE **/
    int temperatureint;
    ArrayList<String> tempintresult;

    /** VARIABLES FOR INTERN TEMPERATURE **/
    int lightint;
    ArrayList<String> lightintresult;

    /*** BUTTON ***/
    JButton validation;
    GoBackButton goBackButton;

    public ElectroChromaAuto(String companyName) {

        this.companyName = companyName;

        /** FRAME SETUP **/

        setSize(600, 400);
        setResizable(false);
        setTitle("Parametres Automatiques Electrochroma");
        box = new Box(BoxLayout.Y_AXIS);
        box.setBackground(Color.WHITE);


        validation = new JButton("VALIDER");
        validation.setSize(100, 100);
        validation.addActionListener(this);

        /** METHODS **/
        getTempExt();
        getAutoLigParameters();
        getAutoTempParameters();

        //Temperature ext part

        Atempext = new JLabel("Temperature exterieur :   " + temperatureext + " °C");

        //Temperature modification part

        Stemp = new JSlider(JSlider.HORIZONTAL, 0, 30, temperatureint);
        Stemp.setMajorTickSpacing(1);
        Stemp.setPaintTicks(true);



        //Light part

        Slight = new JSlider(JSlider.HORIZONTAL, 0, 5000, lightint);
        Slight.setMajorTickSpacing(500);
        Slight.setPaintTicks(true);


        /** TEMPERATURES AND LIGHT **/

        Atempext = new JLabel("Temperature exterieur :" + temperatureext + " °C");
        ATemp = new JLabel("Temperature souhaitée dans les salles : " + temperatureint + " °C");
        ALight = new JLabel("Eclairage souhaitée dans les salles : " + lightint + " lux");

        /** FRAME ADD **/

        //General information
        box.add(Box.createGlue());
        box.add(Atempext);
        box.add(Box.createGlue());

        //Modification part
        box.add(ATemp);
        box.add(Box.createGlue());
        box.add(Stemp);
        box.add(Box.createGlue());
        box.add(ALight);
        box.add(Box.createGlue());
        box.add(Slight);
        box.add(Box.createGlue());

        box2 = new Box(BoxLayout.X_AXIS);
        box2.setBackground(Color.WHITE);
        box2.add(Box.createGlue());
        box2.add(validation);
        box2.add(Box.createGlue());


        eventTemp et = new eventTemp();
        Stemp.addChangeListener(et);

        eventLight el = new eventLight();
        Slight.addChangeListener(el);

        setVisible(true);
    }

    @Override
    //BUTTON CHANGER
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
    //SLIDERS CHANGERS
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

    //OBTAIN VALUES
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
    public void getAutoLigParameters(){

        logger.info("begin getAutoTempLigParameters");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("ligautoparams");
        stringArrayList.add(companyName);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.lightintresult = ccSocketTCP2.result;
        if(!lightintresult.isEmpty()) {
            lightint = Integer.parseInt(lightintresult.get(0));
            logger.info("lightintresult = " + lightint);
        }
    }
    public void getAutoTempParameters(){

        logger.info("begin getAutoTempLigParameters");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("tempautoparams");
        stringArrayList.add(companyName);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.tempintresult = ccSocketTCP2.result;
        if(!tempintresult.isEmpty()) {
            temperatureint = Integer.parseInt(tempintresult.get(0));
            logger.info("temperatureintresult = " + temperatureint);
        }
    }

    //RETURN BUTTON
    public void newGoBack(JFrame j){
        goBackButton = new GoBackButton(this,j);
        box2.add(goBackButton);
        box2.add(Box.createGlue());
        box.add(box2);
        box.add(Box.createGlue());
        getContentPane().add(box,BorderLayout.CENTER);
    }
}