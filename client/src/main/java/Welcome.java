import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome {

    public Welcome() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pane = new JPanel(new BorderLayout());


        JPanel header = new JPanel();
        header.setBackground(new Color(42, 73, 145));
        JLabel title = new JLabel("One Project");
        title.setForeground(Color.WHITE);
        header.add(title);
        pane.add(header, BorderLayout.PAGE_START);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(new Color(128, 152, 207));
        JButton admin = new JButton("Admin");
        admin.setAlignmentX(JButton.LEFT_ALIGNMENT);
        content.add(admin, BorderLayout.LINE_START);
        content.add(new JLabel("Bienvenue, veuillez sélectionnez votre Entreprise"));
        String[] options = { "Google", "Microsoft", "Facebook" };
        JComboBox cbox = new JComboBox(options);
        content.add(cbox);
        pane.add(content, BorderLayout.CENTER);
        JButton ok = new JButton("OK");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.setVisible(false);
                frame.getContentPane().removeAll();
                JPanel panel = new Window().getPan();
                frame.setContentPane(panel);
                frame.invalidate();
                frame.validate();
                frame.repaint();
                frame.pack();
            }
        });
        pane.add(ok, BorderLayout.PAGE_END);


        frame.add(pane);
        frame.setSize(400, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setVisible(true);

    }

}
