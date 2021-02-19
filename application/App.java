import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App() {

        super("One test");
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dany\\Documents\\one-project\\files\\logo.png");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JButton button = new JButton("test");
        JPanel pane = new JPanel();
        pane.add(button);
        setContentPane(pane);
        setSize(500,500);
        setDefaultLookAndFeelDecorated(true);
        setBackground(Color.PINK);
        setBounds(500,250,500,250);
        setIconImage(icon);
        setVisible(true);
        setLayout(null);
    }
}
