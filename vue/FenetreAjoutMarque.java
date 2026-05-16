package vue;
import java.awt.*;
import javax.swing.*;
import modele.*;

public class FenetreAjoutMarque extends JFrame {
    private JTextField txtNom = new JTextField();
    private JTextField txtPays = new JTextField();
    private JButton btnValider = new JButton("Enregistrer la Marque");

    public FenetreAjoutMarque(Parc modeleParc) {
        setTitle("Catalogue - Nouvelle Marque");
        setSize(350, 250);
        setLayout(new BorderLayout(10, 10));
        
        // --- DESIGN SOMBRE ---
        getContentPane().setBackground(new Color(45, 45, 45));

        JPanel p = new JPanel(new GridLayout(2, 2, 10, 20));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        p.add(creerLabel("Nom de la marque :")); p.add(txtNom);
        p.add(creerLabel("Pays d'origine :")); p.add(txtPays);
        add(p, BorderLayout.CENTER);

        JPanel pBas = new JPanel();
        pBas.setOpaque(false);
        styliserBouton(btnValider, new Color(0, 120, 215), Color.WHITE); // Bouton Bleu
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

    private JLabel creerLabel(String texte) {
        JLabel label = new JLabel(texte);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        return label;
    }

    private void styliserBouton(JButton btn, Color fond, Color texte) {
        btn.setBackground(fond);
        btn.setForeground(texte);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}