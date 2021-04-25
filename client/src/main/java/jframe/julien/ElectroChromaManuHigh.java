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

    JSlider storehigh; // --> In progress
    JLabel Astorevalue; // --> In progress

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

        /** SLIDER **/

        storehigh = new JSlider(JSlider.HORIZONTAL, 0, 5,strhigh);
        storehigh.setMajorTickSpacing(1);
        storehigh.setPaintTicks(true);
        Astorevalue = new JLabel("Hauteur du store souhaitée : "+ strhigh);

        /** FRAME ADD **/

        add(Astorevalue);
        add(storehigh);
        add(validation);

        eventStore es = new eventStore();
        storehigh.addChangeListener(es);
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
            stringArrayList.add(storehigh.getValue() + "");
            CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        }
    }

    public class eventStore implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent es) {

            int valuehighstore = storehigh.getValue();
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
}
