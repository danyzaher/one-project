package jframe.Dolf;

import jframe.HPageCompany;
import jframe.maxime.button.GoBackButton;
import jframe.maxime.button.Sensor;
import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Indicators<ind_occ> extends JFrame implements ActionListener {
    private JPanel panel1, panel2,panel3,panel4,panel5,panel6;
    private String ind_occ;
     public Indicators(){
         JButton SensorsP;

         //super(" ");
         super.setFont(new Font(Font.SERIF,Font.BOLD,20));
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setSize(800, 700);
         this.setLocationRelativeTo(null);
         // this.getContentPane().add(new JButton(),BorderLayout.CENTER);
         panel1=new JPanel();
         //panel1.setBackground(Color.WHITE);
         panel1.setPreferredSize(new Dimension(300,200));
         panel3=new JPanel();
         panel3.setPreferredSize(new Dimension(150, 150));
         panel4= new JPanel();
         panel4.setPreferredSize(new Dimension(150, 150));
         panel5= new JPanel();
         panel5.setPreferredSize(new Dimension(150, 150));
         panel6= new JPanel();
         panel6.setPreferredSize(new Dimension(150, 150));

         panel2 =new JPanel();
         //panel2.setBackground(Color.);
         // panel3.setBackground(new Color(128, 152, 207));
         //panel4.setBackground(Color.CYAN);

         this.add(panel1, BorderLayout.SOUTH);
         this.add(panel2, BorderLayout.EAST);
         this.add(panel3,BorderLayout.CENTER);
         this.add(panel4,BorderLayout.PAGE_START);
         //this.add(panel5,BorderLayout.PAGE_END);
         this.add(panel6,BorderLayout.WEST);



         JLabel label= new JLabel(" Mes Indicateurs ");
         label.setFont(new Font(Font.SERIF,Font.BOLD,50));
         panel4.add(label);
         JButton ind_occ = new JButton("Taux d'occupation");
         panel1.add(ind_occ);
         ind_occ.addActionListener(new Room_occupation_Rate());

         SensorsP = new JButton(" nombre de capteurs");
         SensorsP.addActionListener(new Ind_Sensors_number());
         panel6.add(SensorsP);
         JButton water= new JButton(" taux de consommation d'eau");
         panel3.add(water);
        water.addActionListener(new water_s_consumption());
         JButton elect= new JButton("taux de consommation d'electricité");
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
    if (e.getSource().equals(ind_occ)){
        ArrayList<String> al = new ArrayList<>();
       al.add("show");
        al.add("room number");
        CCSocketTCPbis client = new CCSocketTCPbis(al);
        ArrayList<String> strings = client.result;
    }
    }

    public void newGoBack(JFrame j){
        GoBackButton goBackbutton = new GoBackButton(this, j);
        add(goBackbutton);
    }
}
