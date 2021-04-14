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

        ATemp = new JLabel("Valeur actuel : 19°C");
        add(ATemp);

        event e = new event();
        temperature.addChangeListener(e);

    }
    public class event implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {


        }
    }
}
