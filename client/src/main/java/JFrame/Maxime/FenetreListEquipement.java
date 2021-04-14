package JFrame.Maxime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreListEquipement extends JFrame implements ActionListener {
    MapperSalle mapperSalle;

    public FenetreListEquipement(MapperSalle mapperSalle){
        this.mapperSalle=mapperSalle;
        setTitle("liste des équipement disponible pour la salle " + mapperSalle.numeroSalle);
        setVisible(true);
        setSize(400,300);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
