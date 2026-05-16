package vue;
import java.awt.*;
import javax.swing.*;
import modele.*;

public class FenetreAjoutModele extends JFrame {
    private JTextField txtNom = new JTextField();
    private JTextField txtMoteur = new JTextField();
    private JComboBox<Type_permis> comboPermis = new JComboBox<>(Type_permis.values());
    private JComboBox<Marque> comboMarques;
    private JButton btnValider = new JButton("Enregistrer le Modèle");

    public FenetreAjoutModele(Parc modeleParc) {
        setTitle("Catalogue - Nouveau Modèle");
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));
        
        // --- DESIGN SOMBRE ---
        getContentPane().setBackground(new Color(45, 45, 45));

        comboMarques = new JComboBox<>(modeleParc.getCatalogueMarques());

        JPanel p = new JPanel(new GridLayout(4, 2, 10, 20));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        p.add(creerLabel("Marque :")); p.add(comboMarques);
        p.add(creerLabel("Nom du modèle :")); p.add(txtNom);
        p.add(creerLabel("Motorisation :")); p.add(txtMoteur);
        p.add(creerLabel("Permis requis :")); p.add(comboPermis);
        
        add(p, BorderLayout.CENTER);

        JPanel pBas = new JPanel();
        pBas.setOpaque(false);
        styliserBouton(btnValider, new Color(0, 120, 215), Color.WHITE); // Bouton Bleu
        pBas.add(btnValider);
        add(pBas, BorderLayout.SOUTH);

        btnValider.addActionListener(e -> {
            Marque m = (Marque) comboMarques.getSelectedItem();
            if(m != null && !txtNom.getText().isEmpty()) {
                Modele mod = new Modele(txtNom.getText(), txtMoteur.getText(), "Standard", "LED", "2024", (Type_permis)comboPermis.getSelectedItem(), m);
                modeleParc.ajouterModele(mod);
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