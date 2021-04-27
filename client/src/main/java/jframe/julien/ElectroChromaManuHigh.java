package jframe.julien;

import jframe.HPageCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.CCSocketTCPbis;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ElectroChromaManuHigh extends JFrame implements ActionListener {

    private final static Logger logger = LoggerFactory.getLogger(HPageCompany.class.getName());

    /** HIGH STORE VARIABLES **/
    int strhigh;
    String id;
    ArrayList<String> result;

    JSlider Sstorehigh; // --> In progress
    JLabel Astorevalue; // --> In progress

    /** TEMPERATURES AND LIGHT VALUES **/
    //Ext Temp
    int temperatureext;
    ArrayList<String> tempresult;

    //Int Temp
    int temperatureint = 1;
    int lightint = 500;

    JLabel Atempext;
    JLabel Atempint;
    JLabel Alightint;

    /** BUTTON **/
    JButton validation;

    public ElectroChromaManuHigh(String id){

        /** VALIDATION BUTTON **/

        validation = new JButton("VALIDER");
        validation.addActionListener(this);

        /** FRAME SETTINGS **/

        this.id = id;
        setSize(400, 400);
        setResizable(false);
        setTitle("Parametres manuels de l'option electrochroma");
        setLayout(new FlowLayout());

        /** METHOD **/
        getStoreHighValue(); //Get value of the store high
        getTempExt();
        /** SLIDER **/

        Sstorehigh = new JSlider(JSlider.HORIZONTAL, 0, 5,strhigh);
        Sstorehigh.setMajorTickSpacing(1);
        Sstorehigh.setPaintTicks(true);
        Astorevalue = new JLabel("Hauteur du store souhaitée : "+ strhigh);

        /** TEMPERATURES AND LIGHT **/

        Atempext = new JLabel("Temperature exterieur :" + temperatureext + " °C");
        Atempint = new JLabel("Temperature interieur :" + temperatureint + " °C");
        Alightint = new JLabel("Luminosité intérieur de la pièce:"+ lightint + " lux");


        /** FRAME ADD **/

        //General information
        add(Atempext);
        add(Atempint);
        add(Alightint);

        //Modification part
        add(Astorevalue);
        add(Sstorehigh);
        add(validation);

        eventStore es = new eventStore();
        Sstorehigh.addChangeListener(es);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(validation)) {

            logger.info("begin validation");
            ArrayList<String> stringArrayList = new ArrayList<>();
            stringArrayList.add("update");
            stringArrayList.add("strhigh");
            stringArrayList.add(id);
            stringArrayList.add(Sstorehigh.getValue() + "");
            CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        }
    }
    public class eventStore implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent es) {

            int valuehighstore = Sstorehigh.getValue();
            Astorevalue.setText("Hauteur du store selectionné souhaitée : " + valuehighstore);
        }
    }
    public void getStoreHighValue(){

        logger.info("begin getStoreHighValue");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("strhigh");
        stringArrayList.add(id);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.result = ccSocketTCP2.result;
        strhigh = Integer.parseInt(result.get(0));
        logger.info("id = " + strhigh);
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
}
