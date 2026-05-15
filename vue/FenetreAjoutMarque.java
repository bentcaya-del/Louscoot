package vue;
import javax.swing.*;
import java.awt.*;
import modele.*;

public class FenetreAjoutMarque extends JFrame {
    private JTextField txtNom = new JTextField();
    private JTextField txtPays = new JTextField();
    private JButton btnValider = new JButton("Enregistrer la Marque");

    public FenetreAjoutMarque(Parc modeleParc) {
        setTitle("Catalogue - Nouvelle Marque");
        setSize(350, 250);
        setLayout(new BorderLayout(10, 10));

        JPanel p = new JPanel(new GridLayout(2, 2, 10, 20));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        p.add(new JLabel("Nom de la marque :")); p.add(txtNom);
        p.add(new JLabel("Pays d'origine :")); p.add(txtPays);
        add(p, BorderLayout.CENTER);

        JPanel pBas = new JPanel();
        pBas.add(btnValider);
        add(pBas, BorderLayout.SOUTH);

        btnValider.addActionListener(e -> {
            if(!txtNom.getText().isEmpty()) {
                Marque m = new Marque(txtNom.getText(), txtPays.getText());
                modeleParc.ajouterMarque(m);
                JOptionPane.showMessageDialog(this, "Marque ajoutée au catalogue !");
                dispose();
            }
        });
        
        setLocationRelativeTo(null);
    }
}