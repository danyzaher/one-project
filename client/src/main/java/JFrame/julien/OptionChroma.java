package JFrame.julien;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class OptionChroma extends JFrame{
    JSlider temperature;
    JSlider Store;
    JSlider Fenetre;

    JLabel ATemp;
    JLabel AStore;
    JLabel AFen;

    public OptionChroma() {
        setLayout(new FlowLayout());
        temperature = new JSlider(JSlider.HORIZONTAL, 0,30, 19);
        temperature.setMajorTickSpacing(1);
        temperature.setPaintTicks(true);
        add(temperature);

        ATemp = new JLabel("Temperature actuelle dans la salle : 19°C");
        add(ATemp);

        event e = new event();
        temperature.addChangeListener(e);

    }
    public class event implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {

            int valeur = temperature.getValue();
            ATemp.setText("Temperature actuelle dans la salle : "+ valeur + "° C");


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
