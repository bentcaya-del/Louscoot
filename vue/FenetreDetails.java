package vue;
import java.awt.*;
import javax.swing.*;
import modele.*;

public class FenetreDetails extends JFrame {
    
    public FenetreDetails(Parc modele, Scooter scooter, boolean estGerant) {
        setTitle("Détails : " + scooter.getModele().getNom_modele());
        setSize(400, 350);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        
        // --- DESIGN SOMBRE ---
        getContentPane().setBackground(new Color(45, 45, 45));

        JPanel panelInfos = new JPanel(new GridLayout(6, 1, 5, 5));
        panelInfos.setOpaque(false); // Transparent
        panelInfos.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        panelInfos.add(creerLabel("➤ Marque : " + scooter.getModele().getMarque().getNomMarque()));
        panelInfos.add(creerLabel("➤ Modèle : " + scooter.getModele().getNom_modele()));
        panelInfos.add(creerLabel("➤ Motorisation : " + scooter.getModele().getMotorisation()));
        panelInfos.add(creerLabel("➤ Couleur : " + scooter.getColoris()));
        panelInfos.add(creerLabel("➤ Permis requis : " + scooter.getModele().getPermis()));
        
        JLabel prixLabel = new JLabel("Prix : " + scooter.getPrix_jour() + " € / jour");
        prixLabel.setFont(new Font("Arial", Font.BOLD, 15));
        prixLabel.setForeground(new Color(100, 255, 100)); // Vert clair
        panelInfos.add(prixLabel);
        
        add(panelInfos, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        panelBas.setOpaque(false);
        
        if (!estGerant) {
            JButton btnLouer = new JButton("Louer");
            styliserBouton(btnLouer, new Color(0, 120, 215), Color.WHITE);
            btnLouer.addActionListener(e -> {
                new FenetreLouer(modele, scooter).setVisible(true);
                dispose();
            });
            panelBas.add(btnLouer);
        } else {
            JButton btnRetour = new JButton("Gérer le retour");
            styliserBouton(btnRetour, new Color(220, 50, 50), Color.WHITE);
            btnRetour.addActionListener(e -> {
                if (!scooter.getListe_location().isEmpty()) {
                    Location derniereLoc = scooter.getListe_location().lastElement();
                    new FenetreRetour(modele, derniereLoc).setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Ce scooter n'a aucune location en cours.", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
            });
            panelBas.add(btnRetour);
        }

        JButton btnFermer = new JButton("Fermer");
        styliserBouton(btnFermer, new Color(80, 80, 80), Color.WHITE);
        btnFermer.addActionListener(e -> dispose());
        panelBas.add(btnFermer);
        
        add(panelBas, BorderLayout.SOUTH);
    }

    private JLabel creerLabel(String texte) {
        JLabel label = new JLabel(texte);
        label.setForeground(Color.WHITE);
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