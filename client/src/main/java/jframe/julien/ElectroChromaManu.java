package jframe.julien;

import jframe.HPageCompany;
import socket.CCSocketTCPbis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ElectroChromaManu extends JFrame implements ActionListener {

    private final static Logger logger = LoggerFactory.getLogger(HPageCompany.class.getName());

    /** OPACITY VARIABLES **/
    int opacity;
    String id;
    ArrayList<String> result;

    /** TEMPERATURES AND LIGHT INTENSITY **/
    int tempext;
    int tempint;
    int lightintensity;

    /** HIGH STORE VARIABLES **/
    int strhigh;
    //ArrayList<String> result2; ???


    JSlider opac; //--> Check
    JLabel Aopacvalue; //-->Check

    JSlider storehigh; // --> In progress
    JLabel Astorevalue; // --> In progress

    JLabel ATempExt;
    JLabel ATempInt;
    JLabel ALightInt;

    JButton validation;


    public ElectroChromaManu(String id) {

        /** VALIDATION BUTTON **/
        validation = new JButton("VALIDER");
        validation.addActionListener(this);

        /** FRAME SETTINGS **/
        this.id = id;
        setSize(400, 400);
        setResizable(false);
        setTitle("Parametres manuels de l'option electrochroma");
        setLayout(new FlowLayout());

        /** METHODS **/
        getOpacityValue(); //Get value of the opacity of the window
        getStoreHighValue(); //Get value of the store high
        /** SLIDERS **/

        //Opacity
        opac = new JSlider(JSlider.HORIZONTAL, 0, 5, opacity);
        opac.setMajorTickSpacing(1);
        opac.setPaintTicks(true);
        Aopacvalue = new JLabel("Opacité souhaitée dans les salles : " + opacity);

        //Store High
        storehigh = new JSlider(JSlider.HORIZONTAL, 0, 5,strhigh);
        storehigh.setMajorTickSpacing(1);
        storehigh.setPaintTicks(true);
        Astorevalue = new JLabel("Hauteur du store souhaitée : "+ strhigh);

        /** FRAME ADD **/

        add(Aopacvalue);
        add(opac);
        add(Astorevalue);
        add(storehigh);
        add(validation);

        eventOpac eo = new eventOpac();
        eventStore es = new eventStore();

        opac.addChangeListener(eo);
        storehigh.addChangeListener(es);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(validation)) {

        logger.info("begin validation");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("update");
        stringArrayList.add("opacity");
        stringArrayList.add(id);
        stringArrayList.add(opac.getValue() + "");
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);

    }
}

    public class eventOpac implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent eo) {

            int valueopacity = opac.getValue();
            Aopacvalue.setText("Opacité de la fenetre selectionnée souhaité : " + valueopacity);
        }
    }

    public class eventStore implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent es) {

            int valuehighstore = storehigh.getValue();
            Astorevalue.setText("Hauteur du store selectionné souhaitée : " + valuehighstore);
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