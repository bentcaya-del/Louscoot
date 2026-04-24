import javax.swing.*;

public class FenetreRetour extends JFrame {
    public FenetreRetour() {
        setTitle("Fenêtre de Retour");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);
    }
}