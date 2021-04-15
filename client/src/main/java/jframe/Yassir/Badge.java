package JFrame.Yassir;
import javax.swing.* ;
import java.awt.*;

public class Badge extends JFrame {
    public Badge() {
        this.setTitle("OneBuilding");
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel boutons=new JPanel();
        boutons.setLayout(new BoxLayout(boutons, BoxLayout.LINE_AXIS));

        boutons.add(new JButton("Afficher tout"));
        boutons.add(new JButton("Signaler problème"));

        JPanel recherche =new JPanel();
        recherche.add(new JLabel("Rechercher"));

        JPanel nume=new JPanel();
        JTextArea num =new JTextArea();
        num.setPreferredSize(new Dimension(100,20));
        nume.add(num);
        nume.add(new JButton("Afficher "));

        JPanel fenetre=new JPanel();
        fenetre.setLayout(new BoxLayout(fenetre, BoxLayout.PAGE_AXIS));
        fenetre.add(recherche);
        fenetre.add(nume);
        fenetre.add(boutons);

        this.getContentPane().add(fenetre);
        this.setVisible(true);

    }
    public static void main (String [] args) {
        Badge badge = new Badge();
    }
}
