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

public class ElectroChromaManuOpa extends JFrame implements ActionListener {

    private final static Logger logger = LoggerFactory.getLogger(HPageCompany.class.getName());

    /** OPACITY VARIABLES **/
    int opacity;
    String id;
    ArrayList<String> result;

    /** TEMPERATURES AND LIGHT INTENSITY VARIABLES **/

    int temperatureext;
    String idtemp;
    ArrayList<String> tempresult;
    int tempint;
    int lightintensity;

    JSlider opac; //--> Check
    JLabel Aopacvalue; //-->Check

    /** TEMPERATURES AND LIGHT VALUES **/


    int temperatureint = 1;
    int lightint = 500;

    JLabel Atempext;
    JLabel Atempint;
    JLabel Alightint;

    JButton validation;


    public ElectroChromaManuOpa(String id) {

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
        getOpacityValue(); //Get value of the opacity of the window
        getTempExt(); //Get value of the temperature ext
        /** SLIDER **/

        //Opacity
        opac = new JSlider(JSlider.HORIZONTAL, 0, 5, opacity);
        opac.setMajorTickSpacing(1);
        opac.setPaintTicks(true);
        Aopacvalue = new JLabel("Opacité actuelle de la fenetre : " + opacity);

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
        add(Aopacvalue);
        add(opac);
        add(validation);

        eventOpac eo = new eventOpac();
        opac.addChangeListener(eo);

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
            Aopacvalue.setText("Opacité de la fenetre souhaitée : " + valueopacity);
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