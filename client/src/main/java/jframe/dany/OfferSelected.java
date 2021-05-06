package jframe.dany;

import socket.CCSocketTCPbis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OfferSelected extends JFrame implements ActionListener{
    JPanel bigpan = new JPanel();
    JPanel right = new JPanel();
    JPanel left = new JPanel();
    JPanel down = new JPanel();
    JButton rent = new JButton("Louer");

    jframe.dany.OneOffer oneOffer;
    JLabel desc;JLabel desc1;
    String companyName;
    public OfferSelected(jframe.dany.OneOffer on, String companyName) {
        this.companyName=companyName;
        oneOffer = on;
        setTitle("Détails de l'offre");
        setLocationRelativeTo(null);
        setSize(100 + 10*oneOffer.title.getText().length(), 150);
        setResizable(true);
        setLayout(new FlowLayout());
        int[] type = countType();
        desc = new JLabel(oneOffer.getTitle() + "  PRICE : " + oneOffer.getPrice() + " €");
        desc1 = new JLabel("Nombre de salles par type : Bureau = " + type[0] + ", Salle pleniere = " + type[1] + ", Salle de reunion = " + type[2]);

        left.add(desc);
        down.add(desc1);
        right.add(rent);
        rent.addActionListener(this);
        right.setLayout(new BoxLayout(right, BoxLayout.LINE_AXIS));
        left.setLayout(new BoxLayout(left, BoxLayout.LINE_AXIS));
        down.setLayout(new BoxLayout(down, BoxLayout.LINE_AXIS));

        bigpan.add(right);
        bigpan.add(left);
        bigpan.add(down);
        bigpan.setLayout(new BoxLayout(bigpan, BoxLayout.PAGE_AXIS));
        this.add(bigpan);
        setVisible(true);
    }
    //  int[0] Bureau
    //  int[1] Salle pleniere
    //  int[2] Salle de reunion
    public int[] countType() {
        int[] result = new int[3];
        for (String id:oneOffer.getId()) {
            ArrayList<String> commands = new ArrayList<>();
            commands.add("show"); commands.add("company"); commands.add("type");commands.add(id);
            CCSocketTCPbis cc = new CCSocketTCPbis(commands);
                switch (cc.result.get(0)) {
                    case("Reunion") : result[2] += 1; break;
                    case("Pleniere") : result[1] += 1; break;
                    case("Bureau") : result[0] += 1; break;
                }
            }
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == rent) {
            for (int i = 0; i < oneOffer.getId().size(); i++) {
                ArrayList<String> commands = new ArrayList<>();
                commands.add("show");
                commands.add("company");
                commands.add("id");
                commands.add(companyName);
                CCSocketTCPbis cc = new CCSocketTCPbis(commands);
                ArrayList<String> al = new ArrayList<>();
                al.add("insert");
                al.add("location");
                al.add(oneOffer.getId().get(i));
                al.add(cc.result.get(0));
                al.add(oneOffer.getPrice());

                new CCSocketTCPbis(al);
            }
            JOptionPane.showMessageDialog(bigpan, "La location a bien été effectuée", "Succès", JOptionPane.INFORMATION_MESSAGE);

        }
    }
}
