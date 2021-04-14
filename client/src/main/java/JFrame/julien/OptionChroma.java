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
    ImageIcon dispoFenStor;
    JLabel aide;

    /**Upper part of thr frame**/
    JLabel tempext, tempint, eclairage;

    //Selection of windows or store
    JComboBox numstore,numfenetre;

    String [] fenetrestore = {"1","2","3"};

    public OptionChroma() {

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

        ATemp = new JLabel("Temperature actuelle dans la salle : 19°C");
        add(ATemp);
        eventTemp et = new eventTemp();
        temperature.addChangeListener(et);

        //Store lower Part
        store = new JSlider(JSlider.HORIZONTAL, 0,5, 0);
        store.setMajorTickSpacing(1);
        store.setPaintTicks(true);
        add(store);

        AStore = new JLabel("Hauteur actuelle du store : 0");
        add(AStore);
        eventStor es = new eventStor();
        store.addChangeListener(es);

        //Windows lower Part
        fenetre = new JSlider(JSlider.HORIZONTAL, 0,5, 0);
        fenetre.setMajorTickSpacing(1);
        fenetre.setPaintTicks(true);
        add(fenetre);

        AFen = new JLabel("Opacité actuelle de la fenetre : 0 ");
        add(AFen);
        eventFen ef = new eventFen();
        fenetre.addChangeListener(ef);

        /********* HELP PICTURE FOR THE USER *************/

        //dispoFenStor = new ImageIcon(getClass().getResource("test.png"));
        aide = new JLabel(dispoFenStor);
        add(aide);

        /******** JCOMBOBOXES FOR SELECT THE STORE OR WINDOW *******/

        numfenetre = new JComboBox(fenetrestore);
        numstore = new JComboBox(fenetrestore);

        add(numfenetre);
        add(numstore);

    }
    public class eventFen implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {

            int valeurfen = fenetre.getValue();
            AFen.setText("Opacité de la fenetre selectionnée souhaité  : "+valeurfen);
        }
    }
    public class eventStor implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
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
        opchro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        opchro.setSize(300,400);
        opchro.setVisible(true);
        opchro.pack();
        opchro.setTitle("Parametres des Options ElectroChromatiques ");

    }
}
