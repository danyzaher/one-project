import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Window extends JFrame {
    private JPanel pan;
    public Window() {
        pan = new JPanel();
        pan.setBackground(new Color(42, 73, 145));

        Box bigBox = Box.createVerticalBox();
        JLabel jLabel = new JLabel("One Building");
        jLabel.setForeground(Color.WHITE);
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bigBox.add(jLabel);

        JPanel middlePanel = new JPanel();
        Box mediumBox = Box.createVerticalBox();
        JLabel label = new JLabel("Bienvenue, selectionnez votre société");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        middlePanel.setBackground(Color.WHITE);
        middlePanel.setPreferredSize(new Dimension(400, 200));
        mediumBox.setSize(300, 200);
        mediumBox.add(label);

        middlePanel.add(mediumBox);
        bigBox.add(middlePanel);

        JPanel smallPanel = new JPanel(new GridLayout(1, 2, 60, 20));
        Box rectangle = Box.createVerticalBox();
        Box menu = Box.createVerticalBox();

        rectangle.add(new JButton("Enter"));

        rectangle.setBorder(new LineBorder(Color.black));
        JLabel menuLabel = new JLabel("menu");
        menuLabel.setOpaque(true);
        menuLabel.setBackground(new Color(42, 73, 145));
        menuLabel.setBorder(new LineBorder(
                new Color(42, 73, 145)
        ));
        menuLabel.setForeground(Color.WHITE);
        menu.add(menuLabel);
        smallPanel.add(rectangle);
        smallPanel.add(menu);

        mediumBox.add(smallPanel);

        pan.setBorder(new EmptyBorder(5, 10, 30, 10));
        middlePanel.setBorder(new EmptyBorder(5, 10, 30, 10));
        smallPanel.setBorder(new EmptyBorder(5, 10, 30, 10));

        // Ajout du boutton
        pan.add(bigBox);
    }

    public JPanel getPan() {
        return pan;
    }
}
