package JFrame.Yassir;

import javax.swing.*;

public class Verify extends JFrame {
    JLabel label1;
    JLabel label2;
    JComboBox combo;
    JComboBox combo1;
    public Verify(){
        this.setTitle("OneBuilding");
        this.setSize(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        String s[]={"capteur 22","équipement 23","capteur21"};
        String s1[]={"A12A","A13A","B25A"};
        combo=new JComboBox(s);
        combo1=new JComboBox(s1);

        JPanel  panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        label1=new JLabel("Badges");
        panel.add(label1);
        panel.add(combo);

        JPanel panel1  = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS));
        label2= new JLabel("Equipements/Capteurs");
        panel1.add(label2);
        panel1.add(combo1);

        JPanel panel2 = new JPanel();
        panel2.add(panel);
        panel2.add(panel1);
        JButton ver = new JButton("Vérifier");
        panel2.add(ver);

        this.getContentPane().add(panel2);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Verify verify = new Verify();
    }
}






