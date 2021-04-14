package JFrame.julien;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class OptionChroma extends JFrame{

    JSlider temperature;
    JSlider store;
    JSlider fenetre;

    JLabel ATemp;
    JLabel AStore;
    JLabel AFen;

    public OptionChroma() {

        setLayout(new FlowLayout());
        //Temperature part

        temperature = new JSlider(JSlider.HORIZONTAL, 0,30, 19);
        temperature.setMajorTickSpacing(1);
        temperature.setPaintTicks(true);
        add(temperature);

        ATemp = new JLabel("Temperature actuelle dans la salle : 19°C");
        add(ATemp);
        eventTemp et = new eventTemp();
        temperature.addChangeListener(et);

        //Store Part
        setLayout(new FlowLayout());
        store = new JSlider(JSlider.HORIZONTAL, 0,5, 5);
        store.setMajorTickSpacing(1);
        store.setPaintTicks(true);
        add(store);

        AStore = new JLabel("Hauteur actuelle du store : ");
        add(AStore);
        eventStor es = new eventStor();
        store.addChangeListener(es);



    }
    public class eventStor implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            int valeurstor = store.getValue();
            AStore.setText("Hauteur actuelle du store : "+ valeurstor);
        }
    }
    public class eventTemp implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent et) {

            int valeurtemp = temperature.getValue();
            ATemp.setText("Temperature actuelle dans la salle : "+ valeurtemp + "° C");

        }



    }

    public static void main(String[] args) {

        OptionChroma opchro = new OptionChroma();
        opchro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        opchro.setSize(500,200);
        opchro.setVisible(true);
        opchro.setTitle("Parametres des Options ElectroChromatiques ");


    }
}
