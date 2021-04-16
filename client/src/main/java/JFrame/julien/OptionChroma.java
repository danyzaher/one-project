package JFrame.julien;
import Socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.event.*;

public class OptionChroma extends JFrame {

    /*** Lower part of the frame ***/

    //Sliders and Labels
    JSlider temperature, store, window;
    JLabel ATemp, AStore, AWin;

    //Help items
    Image dispoFenStor;
    JLabel help;

    /*** Upper part of the frame ***/

    JLabel tempext, tempint, light;

    //Selection of windows or store
    JComboBox numstore, numwindow;

    //Validation Button
    JButton validation;

    String windowstore[] = {"1", "2", "3"};

    public OptionChroma() {

        //dispoFenStor = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Julien CANNOUX\\one-project\\client\\src\\main\\java\\JFrame\\julien\\aide.jpg");

        setSize(320, 400);
        setResizable(false);
        setTitle("Option ElectroChroma");
        setLayout(new FlowLayout());

        /******** JCOMBOBOXES FOR SELECT THE STORE OR WINDOW *******/

        numwindow = new JComboBox(windowstore);
        numstore = new JComboBox(windowstore);
        //numstore.addActionListener(this);


        validation = new JButton("VALIDER");
        validation.setSize(100, 100);



        tempext = new JLabel("Temperature à l'exterieur : 5°C \n");
        tempint = new JLabel("Temperature dans la salle : 20°C \n");
        light = new JLabel("Taux de luminosité dans la salle : 50%\n");
        add(tempext);
        add(tempint);
        add(light);

        //Temperature lower part

        temperature = new JSlider(JSlider.HORIZONTAL, 0, 30, 19);
        temperature.setMajorTickSpacing(1);
        temperature.setPaintTicks(true);


        ATemp = new JLabel("Temperature souhaitée dans la salle : ... °C");
        add(ATemp);
        add(temperature);
        eventTemp et = new eventTemp();
        temperature.addChangeListener(et);

        //Store lower Part
        store = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
        store.setMajorTickSpacing(1);
        store.setPaintTicks(true);


        AStore = new JLabel("Hauteur du store selectionné souhaité : ...");
        add(AStore);
        add(numstore);
        add(store);
        eventStor es = new eventStor();
        store.addChangeListener(es);

        //Windows lower Part
        window = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
        window.setMajorTickSpacing(1);
        window.setPaintTicks(true);


        AWin = new JLabel("Opacité de la fenetre souhaité : ...");
        add(AWin);
        add(numwindow);
        add(window);

        eventWin ew = new eventWin();
        window.addChangeListener(ew);

        /********* HELP PICTURE FOR THE USER *************/

        //dispoFenStor = new ImageIcon(getClass().getResource("test.png"));
        //aide = new JLabel(dispoFenStor);
        //add(aide);
        //add(dispoFenStor);

        add(validation);

        setVisible(true);

    }


    public class eventWin implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ew) {

            int valeurfen = window.getValue();
            AWin.setText("Opacité de la fenetre selectionnée souhaité  : " + valeurfen);
        }
    }

    public class eventStor implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent es) {
            int valeurstor = store.getValue();
            AStore.setText("Hauteur du store selectionné souhaité : " + valeurstor);
        }
    }

    public class eventTemp implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent et) {

            int valeurtemp = temperature.getValue();
            ATemp.setText("Temperature dans la salle souhaitée : " + valeurtemp + "° C");
        }
    }

    public void getWinStore(){
        //Show the Windows and store of the room
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("room");
        stringArrayList.add("room_s_number");
        stringArrayList.add("name");

    }

    public static void main(String[] args) {

        OptionChroma opchro = new OptionChroma();

    }
}
