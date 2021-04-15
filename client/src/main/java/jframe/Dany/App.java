package jframe.Dany;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class App extends JFrame {
    public App() {
        super("One-Building");
        Image var1 = Toolkit.getDefaultToolkit().getImage("client\\files\\logo.png");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setSize(800, 700);
        setDefaultLookAndFeelDecorated(true);
        this.setBackground(new Color(177, 56, 56));
        this.setIconImage(var1);
        this.setLayout((LayoutManager)null);
        this.setLocationRelativeTo((Component)null);
        this.setResizable(true);
        this.setContentPane(new Panneau());
    }

    public static void main(String[] var0) {
        App var1 = new App();
        var1.setVisible(true);
    }
}