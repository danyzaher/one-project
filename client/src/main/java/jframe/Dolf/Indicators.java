package jframe.Dolf;

import jframe.HPageCompany;
import jframe.maxime.button.GoBackButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Indicators extends JFrame implements ActionListener {
    private JPanel panel1, panel2,panel3,panel4,panel5,panel6;
     public Indicators(){
         //super(" ");
         super.setFont(new Font(Font.SERIF,Font.BOLD,30));
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setSize(600, 300);
         this.setLocationRelativeTo(null);
         // this.getContentPane().add(new JButton(),BorderLayout.CENTER);
         panel1=new JPanel();
         //panel1.setBackground(Color.WHITE);
         panel1.setPreferredSize(new Dimension(400,300));
         panel3=new JPanel();
         panel3.setPreferredSize(new Dimension(150, 150));
         panel4= new JPanel();
         panel4.setPreferredSize(new Dimension(150, 150));
         panel5= new JPanel();
         panel5.setPreferredSize(new Dimension(150, 150));
         panel6= new JPanel();
         panel6.setPreferredSize(new Dimension(150, 150));

         panel2 =new JPanel();
         //panel2.setBackground(Color.WHITE);
         // panel3.setBackground(Color.CYAN);
         //panel4.setBackground(Color.CYAN);

         this.add(panel1, BorderLayout.CENTER);
         this.add(panel2, BorderLayout.EAST);
         this.add(panel3,BorderLayout.EAST);
         this.add(panel4,BorderLayout.PAGE_START);
         this.add(panel5,BorderLayout.PAGE_END);
         this.add(panel6,BorderLayout.WEST);


         JLabel label= new JLabel(" My Indicators ");
         label.setBackground(new Color(128, 152, 207));
         panel4.add(label);
         JButton ind_occ = new JButton("Room occupation Rate");
         panel1.add(ind_occ);
         ind_occ.addActionListener(new Room_occupation_Rate());
         JButton sensors= new JButton(" sensor's number");
         panel6.add(sensors);
         sensors.addActionListener(new Ind_Sensors_number());
         JButton water= new JButton(" water_s_consumption");
         panel3.add(water);
        water.addActionListener(new water_s_consumption());
         JButton elect= new JButton(" electricity consumption");
         panel2.add(elect);
       elect.addActionListener(new electricity_consumption());
         JButton back = new JButton("Back");
         panel5.add(back);

     }

    public Indicators(String title) {
    }

    public static void main(String[] args) {
        Indicators p = new Indicators();

        p.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void newGoBack(HPageCompany hPageCompany) {
    }
    public void newGoBack(JFrame j){
        GoBackButton goBackbutton = new GoBackButton(this, j);
        add(goBackbutton);
    }
}
