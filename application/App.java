import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App() {

        super("One test");
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dany\\Documents\\one-project\\files\\logo.png");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JButton button = new JButton("test");
        setSize(800,700);
        setDefaultLookAndFeelDecorated(true);
        setBackground(Color.PINK);
        setIconImage(icon);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        Panneau pane = new Panneau();
        pane.add(button);
        setContentPane(pane);
    }
    public static void main(String[] arg) {
        App fenetre = new App();
        fenetre.setVisible(true);
    }
}
class Panneau extends JPanel {
    public void paintComponent(Graphics g) {                
        int x1 = this.getWidth()/4;
        int y1 = this.getHeight()/4;                      
        g.drawRect(x1, y1, this.getWidth()/2, this.getHeight()/2);
        g.drawLine(x1+50, y1,x1+50,y1+106);
        
  }       
}
