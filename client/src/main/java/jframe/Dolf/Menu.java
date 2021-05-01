package jframe.Dolf;

import javax.swing.*;

public class Menu extends JFrame {
    JButton menu;
    JButton E;
    public Menu(){
this.setTitle("One Building");
this.setSize(600, 200);
this.setLocationRelativeTo(null);
JPanel pan = new JPanel();
pan.setLayout(new BoxLayout(pan, BoxLayout.LINE_AXIS));
        String opt[] ={"salle101","salle102","salle103","salle104","salle n"};
        JComboBox menu = new JComboBox(opt);
        pan.add(menu);
        JPanel pan1;
        pan1 = new JPanel();
        pan1.setLayout(new BoxLayout(pan1, BoxLayout.Y_AXIS));
        E = new JButton("Enter");
        pan1.add(E);

        this.getContentPane().add(pan1);
        this.setVisible(true);

    }

    public static void main(String[]args){

       Menu  menu = new Menu() ;
    }

}
