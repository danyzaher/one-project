package JFrame.Yassir;
import javax.swing.* ;
import java.awt.*;

public class Badge extends JFrame {
    JButton show;
    JButton showall;
    JButton signal;
    JButton verify;


    public Badge() {
        this.setTitle("OneBuilding");
        this.setSize(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel boutons=new JPanel();
        JPanel recherche=new JPanel();
        JPanel nume=new JPanel();
        JPanel fenetre=new JPanel();

        JLabel search;
        JLabel num;
        JLabel name;
        JTextField num1 = new JTextField() ;
        num1.setPreferredSize(new Dimension(100,20));
        JTextField name1=new JTextField() ;
        name1.setPreferredSize(new Dimension(100,20));

        boutons.setLayout(new BoxLayout(boutons, BoxLayout.LINE_AXIS));
        showall=new JButton("Afficher toute la liste");
        signal=new JButton("Signaler un problème");
        verify=new JButton("Vérifier les autorisations");
        boutons.add(showall);
        boutons.add(signal);
        boutons.add(verify);



        num=new JLabel("Numéro de badge");

        recherche.add(num);
        recherche.add(num1);


        name=new JLabel("Nom de l'employé");

        nume.add(name);
        nume.add(name1);





        fenetre.setLayout(new BoxLayout(fenetre, BoxLayout.PAGE_AXIS));
        search=new JLabel("Recherche");
        show=new JButton("Afficher résultat");
        fenetre.add(search);
        fenetre.add(recherche);
        fenetre.add(nume);
        fenetre.add(show);
        fenetre.add(boutons);

        this.getContentPane().add(fenetre);
        this.setVisible(true);

    }
    public static void main (String [] args) {

        Badge badge = new Badge();
    }
}
