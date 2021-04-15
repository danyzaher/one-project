package JFrame.julien;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class OptionChroma extends JFrame{

    /**Lower part of the frame**/

    //Sliders and Labels
    JSlider temperature, store, fenetre;
    JLabel ATemp, AStore, AFen;

    //Help items
    Image dispoFenStor;
    JLabel aide;

    /**Upper part of thr frame**/
    JLabel tempext, tempint, eclairage;

    //Selection of windows or store
    JComboBox numstore,numfenetre;

    //Validation Button
    JButton validation;

    String fenetrestore []  = {"1","2","3"};

    public OptionChroma() {

        //dispoFenStor = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Julien CANNOUX\\one-project\\client\\src\\main\\java\\JFrame\\julien\\aide.jpeg");

        setSize(320,400);
        setResizable(false);
        setTitle("Option ElectroChroma");
        setLayout(new FlowLayout());

        tempext = new JLabel("Temperature à l'exterieur : 5°C");
        tempint = new JLabel("Temperature dans la salle : 20°C");
        eclairage = new JLabel("Taux de luminosité dans la salle : 50%");
        add(tempext);
        add(tempint);
        add(eclairage);

        //Temperature lower part

        temperature = new JSlider(JSlider.HORIZONTAL, 0,30, 19);
        temperature.setMajorTickSpacing(1);
        temperature.setPaintTicks(true);
        add(temperature);

        ATemp = new JLabel("Temperature souhaitée dans la salle : ... °C");
        add(ATemp);
        eventTemp et = new eventTemp();
        temperature.addChangeListener(et);

        //Store lower Part
        store = new JSlider(JSlider.HORIZONTAL, 0,5, 0);
        store.setMajorTickSpacing(1);
        store.setPaintTicks(true);
        add(store);

        AStore = new JLabel("Hauteur du store selectionné souhaité : ...");
        add(AStore);
        eventStor es = new eventStor();
        store.addChangeListener(es);

        //Windows lower Part
        fenetre = new JSlider(JSlider.HORIZONTAL, 0,5, 0);
        fenetre.setMajorTickSpacing(1);
        fenetre.setPaintTicks(true);
        add(fenetre);

        AFen = new JLabel("Opacité de la fenetre souhaité : ...");
        add(AFen);
        eventFen ef = new eventFen();
        fenetre.addChangeListener(ef);

        /********* HELP PICTURE FOR THE USER *************/

        //dispoFenStor = new ImageIcon(getClass().getResource("test.png"));
        //aide = new JLabel(dispoFenStor);
        //add(aide);
        //add(dispoFenStor);

        /******** JCOMBOBOXES FOR SELECT THE STORE OR WINDOW *******/

        numfenetre = new JComboBox(fenetrestore);
        numstore = new JComboBox(fenetrestore);

        validation = new JButton("VALIDER");
        validation.setSize(100,100);

        add(numfenetre);
        add(numstore);

        add(validation);

        setVisible(true);

    }


    public class eventFen implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent ef) {

            int valeurfen = fenetre.getValue();
            AFen.setText("Opacité de la fenetre selectionnée souhaité  : "+valeurfen);
        }
    }
    public class eventStor implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent es) {
            int valeurstor = store.getValue();
            AStore.setText("Hauteur du store selectionné souhaité : "+ valeurstor);
        }
    }
    public class eventTemp implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent et) {

            int valeurtemp = temperature.getValue();
            ATemp.setText("Temperature dans la salle souhaitée : "+ valeurtemp + "° C");
        }
    }

    public static void main(String[] args) {

        OptionChroma opchro = new OptionChroma();

    }
}
