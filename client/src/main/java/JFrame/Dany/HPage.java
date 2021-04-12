package JFrame.Dany;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HPage extends JFrame implements ActionListener{
    private JButton confirmerButton;
    

    public HPage() {
        this.setVisible(true);
        setTitle("page d'accueil");
        String s1[] = { "Fritel Inc", "FloraFlore" };
        JComboBox jComboBox = null; 
        jComboBox.setModel(new DefaultComboBoxModel(s1));
        add(jComboBox);
    }
    public static void main(String[] args){
        HPage hPage = new HPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
