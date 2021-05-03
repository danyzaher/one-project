package jframe.julien;

import jframe.HPageCompany;
import jframe.maxime.button.Equipment;
import jframe.maxime.button.GoBackButton;
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
    String roomName;
    ArrayList<String> result;

    JSlider Sstorehigh;
    JLabel Astorevalue;

    /** TEMPERATURES AND LIGHT VALUES **/

    //TEMP EXTERN
    int temperatureext;
    ArrayList<String> tempextresult;

    //TEMP INTERN
    String temperatureint;
    ArrayList<String> tempintresult;

    //LIGHT INTERN
    String lightintensity;
    ArrayList<String> lightint;

    //LABELS
    JLabel Atempext;
    JLabel Atempint;
    JLabel Alightint;

    /** BUTTONS **/
    JButton validation;
    GoBackButton goBackButton;

    /** BOXES **/
    private Box box;
    private Box box2;

    public ElectroChromaManuHigh(Equipment equipment){

        this.roomName = equipment.roomName; //RECUPERATION NAME ROOM
        this.id = equipment.id+""; //RECUPERATION ID EQUIPMENT

        /** VALIDATION BUTTON **/

        validation = new JButton("VALIDER");
        validation.addActionListener(this);

        /** FRAME SETTINGS **/

        setSize(400, 400);
        setResizable(false);
        setTitle("Parametres manuels de l'option electrochroma");
        box = new Box(BoxLayout.Y_AXIS);
        box.setBackground(Color.WHITE);

        /** METHOD **/

        getStoreHighValue(); //Get value of the store high
        getTempExt(); //Get value of the temperature ext
        getTempInt(); //Get value of the temperature int
        getLightInt(); // Get value of the light int

        /** SLIDER **/

        Sstorehigh = new JSlider(JSlider.HORIZONTAL, 0, 5,strhigh);
        Sstorehigh.setMajorTickSpacing(1);
        Sstorehigh.setPaintTicks(true);
        Astorevalue = new JLabel("Hauteur du store souhaitée : "+ strhigh);

        /** TEMPERATURES AND LIGHT **/

        Atempext = new JLabel("Temperature exterieur : " + temperatureext + " °C");
        Atempint = new JLabel("Temperature interieur : " + temperatureint + " °C");
        Alightint = new JLabel("Luminosité intérieur de la pièce : "+ lightintensity + " lux");

        /** ADDING PART **/

        //General information
        box.add(Atempext);
        box.add(Box.createGlue());
        box.add(Atempint);
        box.add(Box.createGlue());
        box.add(Alightint);
        box.add(Box.createGlue());

        //Modification part
        box.add(Astorevalue);
        box.add(Box.createGlue());
        box.add(Sstorehigh);
        box.add(Box.createGlue());

        box2 = new Box(BoxLayout.X_AXIS);
        box2.setBackground(Color.WHITE);
        box2.add(Box.createGlue());
        box2.add(validation);
        box2.add(Box.createGlue());

        eventStore es = new eventStore();
        Sstorehigh.addChangeListener(es);

        setVisible(true);

    }

    @Override
    //BUTTON CHANGER
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(validation)) {
            logger.info("begin validation");
            ArrayList<String> strArrayList = new ArrayList<>();
            strArrayList.add("update");
            strArrayList.add("manualmode");
            strArrayList.add(id);
            CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(strArrayList);

            logger.info("begin validation");
            ArrayList<String> stringArrayList = new ArrayList<>();
            stringArrayList.add("update");
            stringArrayList.add("strhigh");
            stringArrayList.add(id);
            stringArrayList.add(Sstorehigh.getValue() + "");
            ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);

            JOptionPane.showMessageDialog(new JPanel(), "LES MODIFICATIONS SE SONT FAITES AVEC SUCCES !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //SLIDER CHANGER
    public class eventStore implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent es) {

            int valuehighstore = Sstorehigh.getValue();
            Astorevalue.setText("Hauteur du store selectionné souhaitée : " + valuehighstore);
        }
    }
    //OBTAIN VALUES
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
        this.tempextresult = ccSocketTCP2.result;
        temperatureext = Integer.parseInt(tempextresult.get(0));
        logger.info("temperatureext = " + temperatureext);

    }
    public void getTempInt(){

        logger.info("begin getTempInt");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("temperatureint");
        stringArrayList.add(roomName);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.tempintresult = ccSocketTCP2.result;
        temperatureint = tempintresult.get(0);
        logger.info("temperatureint = " + temperatureint);

    }
    public void getLightInt(){

        logger.info("begin getLightInt");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("show");
        stringArrayList.add("lightint");
        stringArrayList.add(roomName);
        CCSocketTCPbis ccSocketTCP2 = new CCSocketTCPbis(stringArrayList);
        this.lightint= ccSocketTCP2.result;
        lightintensity = lightint.get(0);
        logger.info("lightint = " + lightintensity);
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
