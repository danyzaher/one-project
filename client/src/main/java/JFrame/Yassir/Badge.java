package JFrame.Yassir;
import javax.swing.* ;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Badge extends JFrame implements ActionListener {
    JButton show;
    JButton showall;
    JButton signal;
    JButton verify;
    JButton infos;


    public Badge() {
        this.setTitle("OneBuilding");
        this.setSize(600, 200);
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
        showall.addActionListener(this);
        signal=new JButton("Signaler un problème");
        signal.addActionListener(this);
        verify=new JButton("Vérifier les autorisations");
        verify.addActionListener(this);
        infos=new JButton("Informations");
        infos.addActionListener(this);
        boutons.add(showall);
        boutons.add(signal);
        boutons.add(verify);
        boutons.add(infos);



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

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source =evt.getSource();

        if (source == showall)
        {
            Showall showall=new Showall();
            showall.setVisible(true);
        }
        else if (source == verify) {
            Verify verif = new Verify();
        }
        else if(source == signal) {
            Problem prob = new Problem();
            prob.setVisible(true);
        }
        else if(source == infos)
        {
            Information info = new Information();
            info.setVisible(true);
        }


    }


    }
